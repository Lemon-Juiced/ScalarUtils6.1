package site.scalarstudios.scalarutils.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.ScalarUtilsItems;

import java.util.function.UnaryOperator;

public class ScalarUtilsBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ScalarUtils.MODID);

    // Liminal Tiles
    public static final DeferredBlock<Block> LIMINAL_TILES = registerBlock("liminal_tiles", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> BRIGHT_LIMINAL_TILES = registerBlock("bright_liminal_tiles", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 15).mapColor(MapColor.SNOW));

    // Liminal Voids
    public static final DeferredBlock<Block> LIMINAL_VOID = registerBlock("liminal_void", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> BRIGHT_LIMINAL_VOID = registerBlock("bright_liminal_void", properties -> properties.strength(2.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 15).mapColor(MapColor.COLOR_BLACK));

    // Penumbral Ore + Umbral Blocks
    public static final DeferredBlock<Block> PENUMBRAL_ORE = registerBlock("penumbral_ore", properties -> properties.strength(30.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.SAND));
    public static final DeferredBlock<Block> UMBRAL_SHARD_BLOCK = registerBlock("umbral_shard_block", properties -> properties.strength(50.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 5).mapColor(MapColor.COLOR_PURPLE));

    private static DeferredBlock<Block> registerBlock(String name, UnaryOperator<BlockBehaviour.Properties> properties) {
        DeferredBlock<Block> toReturn = BLOCKS.registerSimpleBlock(name, properties);
        registerBlockItem(toReturn);
        return toReturn;
    }

    private static void registerBlockItem(DeferredBlock<Block> block) {
        ScalarUtilsItems.ITEMS.registerSimpleBlockItem(block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
