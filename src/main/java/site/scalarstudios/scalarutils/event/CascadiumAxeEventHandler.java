package site.scalarstudios.scalarutils.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.CascadiumAxeItem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Coordinates delayed tree-felling for the Cascadium Axe.
 */
@EventBusSubscriber(modid = ScalarUtils.MODID)
public class CascadiumAxeEventHandler {
    private static final int TICKS_BETWEEN_BREAKS = 2;
    private static final int MAX_LOG_BLOCKS = 256;
    private static final int MAX_LEAF_BLOCKS = 384;
    private static final int LEAF_SEARCH_RADIUS_XZ = 4;
    private static final int LEAF_SEARCH_RADIUS_Y = 3;

    private static final Map<UUID, TreeFellingJob> ACTIVE_JOBS = new HashMap<>();
    private static final Set<GlobalPos> MANAGED_BREAKS = new HashSet<>();

    private CascadiumAxeEventHandler() {}

    public static boolean isManagedBreak(ServerLevel level, BlockPos pos) {
        return MANAGED_BREAKS.contains(GlobalPos.of(level.dimension(), pos));
    }

    public static void queueTreeFelling(ServerPlayer player, ServerLevel level, BlockPos brokenPos, BlockState brokenState) {
        BlockPos origin = brokenPos.immutable();
        List<BlockPos> blocksToBreak = discoverTree(level, origin, brokenState);
        if (blocksToBreak.isEmpty()) {
            ACTIVE_JOBS.remove(player.getUUID());
            return;
        }

        ACTIVE_JOBS.put(player.getUUID(), new TreeFellingJob(level.dimension(), new ArrayDeque<>(blocksToBreak)));
    }

    @SubscribeEvent
    public static void onBlockBreak(BreakBlockEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level) || !(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        BlockPos pos = event.getPos();
        if (isManagedBreak(level, pos)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof CascadiumAxeItem)) {
            return;
        }

        BlockState state = event.getState();
        if (!isLog(state)) {
            return;
        }

        // Keep the clicked anchor log intact while the timed job clears the rest.
        event.setCanceled(true);

        if (ACTIVE_JOBS.containsKey(player.getUUID())) {
            return;
        }

        queueTreeFelling(player, level, pos, state);
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level) || ACTIVE_JOBS.isEmpty()) {
            return;
        }

        Iterator<Map.Entry<UUID, TreeFellingJob>> iterator = ACTIVE_JOBS.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, TreeFellingJob> entry = iterator.next();
            TreeFellingJob job = entry.getValue();

            if (!job.dimension().equals(level.dimension())) {
                continue;
            }

            if (!job.tick(level, entry.getKey())) {
                iterator.remove();
            }
        }
    }

    private static List<BlockPos> discoverTree(ServerLevel level, BlockPos origin, BlockState originState) {
        Set<BlockPos> logBlocks = new HashSet<>();
        ArrayDeque<BlockPos> logQueue = new ArrayDeque<>();
        Set<BlockPos> visitedLogs = new HashSet<>();

        BlockPos start = origin.immutable();
        logQueue.add(start);
        visitedLogs.add(start);

        SearchBounds bounds = new SearchBounds(origin.getX(), origin.getX(), origin.getY(), origin.getY(), origin.getZ(), origin.getZ());

        while (!logQueue.isEmpty()) {
            BlockPos current = logQueue.removeFirst();
            BlockState currentState = current.equals(origin) ? originState : level.getBlockState(current);
            if (!isLog(currentState)) {
                continue;
            }

            bounds.include(current);
            logBlocks.add(current);
            if (logBlocks.size() > MAX_LOG_BLOCKS) {
                return List.of();
            }

            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetY = -1; offsetY <= 1; offsetY++) {
                    for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                        if (offsetX == 0 && offsetY == 0 && offsetZ == 0) {
                            continue;
                        }

                        BlockPos neighbor = current.offset(offsetX, offsetY, offsetZ).immutable();
                        if (visitedLogs.add(neighbor) && isLog(level.getBlockState(neighbor))) {
                            logQueue.add(neighbor);
                        }
                    }
                }
            }
        }

        if (logBlocks.isEmpty()) {
            return List.of();
        }

        Set<BlockPos> leafBlocks = discoverLeaves(level, bounds.expand(LEAF_SEARCH_RADIUS_XZ, LEAF_SEARCH_RADIUS_Y), origin, logBlocks);
        if (logBlocks.size() <= 1) {
            return List.of();
        }

        List<BlockPos> orderedBlocks = new ArrayList<>(logBlocks.size() + leafBlocks.size());
        orderedBlocks.addAll(logBlocks);
        orderedBlocks.addAll(leafBlocks);
        orderedBlocks.sort(Comparator.<BlockPos>comparingInt(BlockPos::getY)
                .reversed()
                .thenComparingInt(pos -> leafBlocks.contains(pos) ? 0 : 1)
                .thenComparingInt(pos -> Math.abs(pos.getX() - origin.getX()) + Math.abs(pos.getZ() - origin.getZ()))
                .thenComparingInt(pos -> pos.equals(origin) ? 1 : 0));

        return orderedBlocks;
    }

    private static Set<BlockPos> discoverLeaves(ServerLevel level, SearchBounds bounds, BlockPos origin, Set<BlockPos> logBlocks) {
        Set<BlockPos> leaves = new HashSet<>();
        Set<BlockPos> visitedLeaves = new HashSet<>();
        ArrayDeque<BlockPos> leafQueue = new ArrayDeque<>();

        Set<BlockPos> anchors = new HashSet<>(logBlocks);
        anchors.add(origin.immutable());

        for (BlockPos anchor : anchors) {
            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetY = -1; offsetY <= 1; offsetY++) {
                    for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                        if (offsetX == 0 && offsetY == 0 && offsetZ == 0) {
                            continue;
                        }

                        BlockPos candidate = anchor.offset(offsetX, offsetY, offsetZ).immutable();
                        if (!bounds.contains(candidate) || !visitedLeaves.add(candidate)) {
                            continue;
                        }

                        if (isLeaf(level.getBlockState(candidate))) {
                            leaves.add(candidate);
                            leafQueue.add(candidate);

                            if (leaves.size() > MAX_LEAF_BLOCKS) {
                                return Set.of();
                            }
                        }
                    }
                }
            }
        }

        while (!leafQueue.isEmpty()) {
            BlockPos current = leafQueue.removeFirst();

            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetY = -1; offsetY <= 1; offsetY++) {
                    for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                        if (offsetX == 0 && offsetY == 0 && offsetZ == 0) {
                            continue;
                        }

                        BlockPos neighbor = current.offset(offsetX, offsetY, offsetZ).immutable();
                        if (!bounds.contains(neighbor) || !visitedLeaves.add(neighbor)) {
                            continue;
                        }

                        if (isLeaf(level.getBlockState(neighbor))) {
                            leaves.add(neighbor);
                            leafQueue.add(neighbor);

                            if (leaves.size() > MAX_LEAF_BLOCKS) {
                                return Set.of();
                            }
                        }
                    }
                }
            }
        }

        return leaves;
    }

    private static boolean isLog(BlockState state) {
        return state.is(BlockTags.LOGS);
    }

    private static boolean isLeaf(BlockState state) {
        return state.is(BlockTags.LEAVES);
    }

    private static final class TreeFellingJob {
        private final ResourceKey<Level> dimension;
        private final ArrayDeque<BlockPos> pendingBlocks;
        private int cooldown;

        private TreeFellingJob(ResourceKey<Level> dimension, ArrayDeque<BlockPos> pendingBlocks) {
            this.dimension = dimension;
            this.pendingBlocks = pendingBlocks;
        }

        private ResourceKey<Level> dimension() {
            return dimension;
        }

        private boolean tick(ServerLevel level, UUID playerId) {
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(playerId);
            if (player == null || !player.isAlive()) {
                return false;
            }

            ItemStack heldItem = player.getMainHandItem();
            if (!(heldItem.getItem() instanceof CascadiumAxeItem)) {
                return false;
            }

            if (cooldown > 0) {
                cooldown--;
                return true;
            }

            while (!pendingBlocks.isEmpty()) {
                BlockPos target = pendingBlocks.removeFirst();
                if (!level.isLoaded(target)) {
                    return false;
                }

                BlockState targetState = level.getBlockState(target);
                if (!isLog(targetState) && !isLeaf(targetState)) {
                    continue;
                }

                GlobalPos managedPos = GlobalPos.of(level.dimension(), target);
                MANAGED_BREAKS.add(managedPos);
                boolean destroyed;
                try {
                    destroyed = player.gameMode.destroyBlock(target);
                } finally {
                    MANAGED_BREAKS.remove(managedPos);
                }

                if (!destroyed) {
                    return false;
                }

                SoundType soundType = targetState.getSoundType(level, target, player);
                level.playSound(
                        null,
                        target,
                        soundType.getBreakSound(),
                        SoundSource.BLOCKS,
                        (soundType.getVolume() + 1.0F) / 2.0F,
                        soundType.getPitch() * 0.8F
                );

                cooldown = Math.max(0, TICKS_BETWEEN_BREAKS - 1);
                return !pendingBlocks.isEmpty();
            }

            return false;
        }
    }


    private static final class SearchBounds {
        private int minX;
        private int maxX;
        private int minY;
        private int maxY;
        private int minZ;
        private int maxZ;

        private SearchBounds(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
            this.minZ = minZ;
            this.maxZ = maxZ;
        }

        private void include(BlockPos pos) {
            minX = Math.min(minX, pos.getX());
            maxX = Math.max(maxX, pos.getX());
            minY = Math.min(minY, pos.getY());
            maxY = Math.max(maxY, pos.getY());
            minZ = Math.min(minZ, pos.getZ());
            maxZ = Math.max(maxZ, pos.getZ());
        }

        private SearchBounds expand(int horizontal, int vertical) {
            return new SearchBounds(
                    minX - horizontal,
                    maxX + horizontal,
                    minY - vertical,
                    maxY + vertical,
                    minZ - horizontal,
                    maxZ + horizontal
            );
        }

        private boolean contains(BlockPos pos) {
            return pos.getX() >= minX && pos.getX() <= maxX
                    && pos.getY() >= minY && pos.getY() <= maxY
                    && pos.getZ() >= minZ && pos.getZ() <= maxZ;
        }
    }
}

