package site.scalarstudios.scalarutils.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.ScalarUtilsItems;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ScalarUtilsBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ScalarUtils.MODID);

    // Dusk Stones
    public static final DeferredBlock<Block> DUSK_STONE = registerBlock("dusk_stone", properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SMOOTH_DUSK_STONE = registerBlock("smooth_dusk_stone", properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<SlabBlock> DUSK_STONE_SLAB = registerSlabBlock("dusk_stone_slab", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final DeferredBlock<SlabBlock> SMOOTH_DUSK_STONE_SLAB = registerSlabBlock("smooth_dusk_stone_slab", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final DeferredBlock<StairBlock> DUSK_STONE_STAIRS = registerStairBlock("dusk_stone_stairs", DUSK_STONE, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<StairBlock> SMOOTH_DUSK_STONE_STAIRS = registerStairBlock("smooth_dusk_stone_stairs", SMOOTH_DUSK_STONE, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> DUSK_STONE_BRICKS = registerBlock("dusk_stone_bricks", properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<SlabBlock> DUSK_STONE_BRICK_SLAB = registerSlabBlock("dusk_stone_brick_slab", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<StairBlock> DUSK_STONE_BRICK_STAIRS = registerStairBlock("dusk_stone_brick_stairs", DUSK_STONE_BRICKS, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<WallBlock> DUSK_STONE_BRICK_WALL = registerWallBlock("dusk_stone_brick_wall", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> CHISELED_DUSK_STONE_BRICKS = registerBlock("chiseled_dusk_stone_bricks", properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CRACKED_DUSK_STONE_BRICKS = registerBlock("cracked_dusk_stone_bricks", properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MOSSY_DUSK_STONE_BRICKS = registerBlock("mossy_dusk_stone_bricks", properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<SlabBlock> MOSSY_DUSK_STONE_BRICK_SLAB = registerSlabBlock("mossy_dusk_stone_brick_slab", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<StairBlock> MOSSY_DUSK_STONE_BRICK_STAIRS = registerStairBlock("mossy_dusk_stone_brick_stairs", MOSSY_DUSK_STONE_BRICKS, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<WallBlock> MOSSY_DUSK_STONE_BRICK_WALL = registerWallBlock("mossy_dusk_stone_brick_wall", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));

    // Liminal Tiles
    public static final DeferredBlock<Block> LIMINAL_TILES = registerBlock("liminal_tiles", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> BRIGHT_LIMINAL_TILES = registerBlock("bright_liminal_tiles", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 15).mapColor(MapColor.SNOW));

    // Liminal Voids
    public static final DeferredBlock<Block> LIMINAL_VOID = registerBlock("liminal_void", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> BRIGHT_LIMINAL_VOID = registerBlock("bright_liminal_void", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 15).mapColor(MapColor.COLOR_BLACK));

    // Resource Blocks
    public static final DeferredBlock<Block> VERDITE_BLOCK = registerBlock("verdite_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> PELAGIUM_BLOCK = registerBlock("pelagium_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> RED_DIAMOND_BLOCK = registerBlock("red_diamond_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.COLOR_RED));
    public static final DeferredBlock<Block> UMBRALITE_BLOCK = registerBlock("umbralite_block", properties -> properties.strength(50.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> UMBRAL_SHARD_BLOCK = registerBlock("umbral_shard_block", properties -> properties.strength(50.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.COLOR_PURPLE));

    // Ores
    public static final DeferredBlock<DropExperienceBlock> PELAGIUM_ORE = registerBlock("pelagium_ore",properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_PELAGIUM_ORE = registerBlock("deepslate_pelagium_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(4.5F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));
    public static final DeferredBlock<DropExperienceBlock> PENUMBRAL_ORE = registerBlock("penumbral_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(30.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.SAND));
    public static final DeferredBlock<DropExperienceBlock> RED_DIAMOND_ORE = registerBlock("red_diamond_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.NETHER));
    public static final DeferredBlock<DropExperienceBlock> VERDITE_ORE = registerBlock("verdite_ore",properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_VERDITE_ORE = registerBlock("deepslate_verdite_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(4.5F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));

    // Raw Resource Blocks
    public static final DeferredBlock<Block> RAW_PELAGIUM_BLOCK = registerBlock("raw_pelagium_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> RAW_VERDITE_BLOCK = registerBlock("raw_verdite_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));

    private static DeferredBlock<Block> registerBlock(String name, UnaryOperator<BlockBehaviour.Properties> properties) {
        return registerBlock(name, Block::new, properties);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties properties) {
        return registerBlock(name, blockFactory, ignored -> properties);
    }

    private static DeferredBlock<SlabBlock> registerSlabBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, SlabBlock::new, properties);
    }

    private static DeferredBlock<StairBlock> registerStairBlock(String name, DeferredBlock<? extends Block> baseBlock, BlockBehaviour.Properties properties) {
        return registerBlock(name, blockProperties -> new StairBlock(baseBlock.get().defaultBlockState(), blockProperties), properties);
    }

    private static DeferredBlock<WallBlock> registerWallBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, WallBlock::new, properties);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, UnaryOperator<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, blockFactory, properties);
        registerBlockItem(toReturn);
        return toReturn;
    }

    private static void registerBlockItem(DeferredBlock<? extends Block> block) {
        ScalarUtilsItems.ITEMS.registerSimpleBlockItem(block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
