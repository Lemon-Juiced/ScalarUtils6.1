package site.scalarstudios.scalarutils.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.EclipseBellItem;
import site.scalarstudios.scalarutils.item.custom.GlueItem;
import site.scalarstudios.scalarutils.item.custom.SawItem;
import site.scalarstudios.scalarutils.item.custom.StormHornItem;


public class ScalarUtilsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ScalarUtils.MODID);

    // Crafting Items
    public static final DeferredItem<GlueItem> GLUE = ITEMS.registerItem("glue", GlueItem::new);
    public static final DeferredItem<Item> FLOUR = ITEMS.registerSimpleItem("flour");
    public static final DeferredItem<Item> MAIL = ITEMS.registerSimpleItem("mail");
    public static final DeferredItem<Item> UMBRAL_SHARD = ITEMS.registerSimpleItem("umbral_shard");

    // Food Items
    public static final DeferredItem<Item> TOAST = ITEMS.registerSimpleItem("toast", p -> p.food(new FoodProperties.Builder().nutrition(8).saturationModifier(1.0F).build()));

    // Tools
    public static final DeferredItem<StormHornItem> STORM_HORN = ITEMS.registerItem("storm_horn", StormHornItem::new);
    public static final DeferredItem<EclipseBellItem> ECLIPSE_BELL = ITEMS.registerItem("eclipse_bell", EclipseBellItem::new);
    public static final DeferredItem<SawItem> WOODEN_SAW = ITEMS.registerItem("wooden_saw", p -> new SawItem(ToolMaterial.WOOD, -3.2F, p));
    public static final DeferredItem<SawItem> STONE_SAW = ITEMS.registerItem("stone_saw", p -> new SawItem(ToolMaterial.STONE, -3.2F, p));
    public static final DeferredItem<SawItem> IRON_SAW = ITEMS.registerItem("iron_saw", p -> new SawItem(ToolMaterial.IRON, -3.1F, p));
    public static final DeferredItem<SawItem> GOLDEN_SAW = ITEMS.registerItem("golden_saw", p -> new SawItem(ToolMaterial.GOLD, -3.2F, p));
    public static final DeferredItem<SawItem> DIAMOND_SAW = ITEMS.registerItem("diamond_saw", p -> new SawItem(ToolMaterial.DIAMOND, -3.0F, p));
    public static final DeferredItem<SawItem> NETHERITE_SAW = ITEMS.registerItem("netherite_saw", p -> new SawItem(ToolMaterial.NETHERITE, -3.0F, p));
    public static final DeferredItem<SawItem> COPPER_SAW = ITEMS.registerItem("copper_saw", p -> new SawItem(ToolMaterial.COPPER, -3.2F, p));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
