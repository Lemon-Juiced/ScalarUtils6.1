package site.scalarstudios.scalarutils.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.block.ScalarUtilsBlocks;

public class ScalarUtilsCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScalarUtils.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALARUTILS_BLOCKS_TAB = CREATIVE_MODE_TABS.register("scalarutils_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.blocks"))
            .icon(() -> new ItemStack(ScalarUtilsBlocks.LIMINAL_TILES.get().asItem()))
            .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALARUTILS_ITEMS_TAB = CREATIVE_MODE_TABS.register("scalarutils_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.items"))
            .icon(() -> new ItemStack(ScalarUtilsItems.FLOUR.get()))
            .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALARUTILS_FOOD_TAB = CREATIVE_MODE_TABS.register("scalarutils_food", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.food"))
            .icon(() -> new ItemStack(ScalarUtilsItems.BOILED_EGG.get()))
            .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALARUTILS_TOOLS_TAB = CREATIVE_MODE_TABS.register("scalarutils_tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.tools"))
            .icon(() -> new ItemStack(ScalarUtilsItems.ECLIPSE_BELL.get()))
            .build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == SCALARUTILS_BLOCKS_TAB.get()) {
            event.accept(ScalarUtilsBlocks.LIMINAL_TILES.get());
            event.accept(ScalarUtilsBlocks.BRIGHT_LIMINAL_TILES.get());
            event.accept(ScalarUtilsBlocks.LIMINAL_VOID.get());
            event.accept(ScalarUtilsBlocks.BRIGHT_LIMINAL_VOID.get());
            event.accept(ScalarUtilsBlocks.CHELITE_BLOCK.get());
            event.accept(ScalarUtilsBlocks.PELAGIUM_BLOCK.get());
            event.accept(ScalarUtilsBlocks.UMBRALITE_BLOCK.get());
            event.accept(ScalarUtilsBlocks.UMBRAL_SHARD_BLOCK.get());
            event.accept(ScalarUtilsBlocks.CHELITE_ORE.get());
            event.accept(ScalarUtilsBlocks.DEEPSLATE_CHELITE_ORE.get());
            event.accept(ScalarUtilsBlocks.PELAGIUM_ORE.get());
            event.accept(ScalarUtilsBlocks.DEEPSLATE_PELAGIUM_ORE.get());
            event.accept(ScalarUtilsBlocks.PENUMBRAL_ORE.get());
            event.accept(ScalarUtilsBlocks.RAW_CHELITE_BLOCK.get());
            event.accept(ScalarUtilsBlocks.RAW_PELAGIUM_BLOCK.get());
        } else if (event.getTab() == SCALARUTILS_FOOD_TAB.get()) {
            event.accept(ScalarUtilsItems.BOILED_EGG.get());
            event.accept(ScalarUtilsItems.TOAST.get());
        } else if (event.getTab() == SCALARUTILS_ITEMS_TAB.get()) {
            event.accept(ScalarUtilsItems.FLOUR.get());
            event.accept(ScalarUtilsItems.GLUE.get());
            event.accept(ScalarUtilsItems.MAIL.get());
            event.accept(ScalarUtilsItems.UMBRALITE_SMITHING_TEMPLATE.get());
            event.accept(ScalarUtilsItems.RAW_CHELITE.get());
            event.accept(ScalarUtilsItems.RAW_PELAGIUM.get());
            event.accept(ScalarUtilsItems.CHELITE_INGOT.get());
            event.accept(ScalarUtilsItems.PELAGIUM_INGOT.get());
            event.accept(ScalarUtilsItems.TESTUDINE_INGOT.get());
            event.accept(ScalarUtilsItems.UMBRALITE_INGOT.get());
            event.accept(ScalarUtilsItems.UMBRAL_SHARD.get());
            event.accept(ScalarUtilsItems.CHELITE_DUST.get());
            event.accept(ScalarUtilsItems.PELAGIUM_DUST.get());
        } else if (event.getTab() == SCALARUTILS_TOOLS_TAB.get()) {
            event.accept(ScalarUtilsItems.UMBRALITE_SWORD.get());
            event.accept(ScalarUtilsItems.UMBRALITE_PICKAXE.get());
            event.accept(ScalarUtilsItems.UMBRALITE_AXE.get());
            event.accept(ScalarUtilsItems.UMBRALITE_SHOVEL.get());
            event.accept(ScalarUtilsItems.UMBRALITE_HOE.get());
            event.accept(ScalarUtilsItems.UMBRALITE_SPEAR.get());
            event.accept(ScalarUtilsItems.WOODEN_SAW.get());
            event.accept(ScalarUtilsItems.STONE_SAW.get());
            event.accept(ScalarUtilsItems.COPPER_SAW.get());
            event.accept(ScalarUtilsItems.IRON_SAW.get());
            event.accept(ScalarUtilsItems.GOLDEN_SAW.get());
            event.accept(ScalarUtilsItems.DIAMOND_SAW.get());
            event.accept(ScalarUtilsItems.NETHERITE_SAW.get());
            event.accept(ScalarUtilsItems.UMBRALITE_SAW.get());
            event.accept(ScalarUtilsItems.STORM_HORN.get());
            event.accept(ScalarUtilsItems.ECLIPSE_BELL.get());
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
