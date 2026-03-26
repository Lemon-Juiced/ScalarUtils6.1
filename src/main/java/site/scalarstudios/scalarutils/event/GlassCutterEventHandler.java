package site.scalarstudios.scalarutils.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.GlassCutterItem;

@EventBusSubscriber(modid = ScalarUtils.MODID)
public class GlassCutterEventHandler {

    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent event) {
        // Only act when the breaking tool is a GlassCutter
        ItemStack tool = event.getTool();
        if (!(tool.getItem() instanceof GlassCutterItem)) return;

        // Only act on blocks in the cuttable list
        Block block = event.getState().getBlock();
        if (!GlassCutterItem.CUTTABLE_BLOCKS.contains(block)) return;

        // Replace whatever the block would normally drop with the block itself
        ItemStack blockDrop = new ItemStack(block.asItem());
        if (blockDrop.isEmpty()) return; // safety – blocks with no item form (e.g. frosted ice)

        event.getDrops().clear();

        BlockPos pos = event.getPos();
        event.getDrops().add(new ItemEntity(
                event.getLevel(),
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                blockDrop
        ));
    }
}

