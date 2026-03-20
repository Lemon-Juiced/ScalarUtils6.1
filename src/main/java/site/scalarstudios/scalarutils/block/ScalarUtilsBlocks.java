package site.scalarstudios.scalarutils.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
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

    // Liminal Tiles
    public static final DeferredBlock<Block> LIMINAL_TILES = registerBlock("liminal_tiles", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> BRIGHT_LIMINAL_TILES = registerBlock("bright_liminal_tiles", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 15).mapColor(MapColor.SNOW));

    // Liminal Voids
    public static final DeferredBlock<Block> LIMINAL_VOID = registerBlock("liminal_void", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> BRIGHT_LIMINAL_VOID = registerBlock("bright_liminal_void", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 15).mapColor(MapColor.COLOR_BLACK));

    // Resource Blocks
    public static final DeferredBlock<Block> CHELITE_BLOCK = registerBlock("chelite_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> PELAGIUM_BLOCK = registerBlock("pelagium_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> UMBRALITE_BLOCK = registerBlock("umbralite_block", properties -> properties.strength(50.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> UMBRAL_SHARD_BLOCK = registerBlock("umbral_shard_block", properties -> properties.strength(50.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.COLOR_PURPLE));

    // Ores
    public static final DeferredBlock<DropExperienceBlock> CHELITE_ORE = registerBlock("chelite_ore",properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_CHELITE_ORE = registerBlock("deepslate_chelite_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(4.5F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));
    public static final DeferredBlock<DropExperienceBlock> PELAGIUM_ORE = registerBlock("pelagium_ore",properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_PELAGIUM_ORE = registerBlock("deepslate_pelagium_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), properties -> properties.strength(4.5F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));
    public static final DeferredBlock<Block> PENUMBRAL_ORE = registerBlock("penumbral_ore", properties -> properties.strength(30.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.SAND));

    // Raw Resource Blocks
    public static final DeferredBlock<Block> RAW_CHELITE_BLOCK = registerBlock("raw_chelite_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> RAW_PELAGIUM_BLOCK = registerBlock("raw_pelagium_block", properties -> properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.IRON).mapColor(MapColor.COLOR_BLUE));

    private static DeferredBlock<Block> registerBlock(String name, UnaryOperator<BlockBehaviour.Properties> properties) {
        return registerBlock(name, Block::new, properties);
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
