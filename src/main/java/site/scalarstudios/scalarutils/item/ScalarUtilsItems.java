package site.scalarstudios.scalarutils.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
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

    // Items
    /* Crafting Items */
    public static final DeferredItem<GlueItem> GLUE = ITEMS.registerItem("glue", GlueItem::new);
    public static final DeferredItem<Item> FLOUR = ITEMS.registerSimpleItem("flour");
    public static final DeferredItem<Item> MAIL = ITEMS.registerSimpleItem("mail");
    public static final DeferredItem<Item> UMBRALITE_SMITHING_TEMPLATE = ITEMS.registerSimpleItem("umbralite_smithing_template");

    /* Resources */
    public static final DeferredItem<Item> UMBRAL_SHARD = ITEMS.registerSimpleItem("umbral_shard");
    public static final DeferredItem<Item> CHELITE_INGOT = ITEMS.registerSimpleItem("chelite_ingot");
    public static final DeferredItem<Item> RAW_CHELITE = ITEMS.registerSimpleItem("raw_chelite");
    public static final DeferredItem<Item> PELAGIUM_INGOT = ITEMS.registerSimpleItem("pelagium_ingot");
    public static final DeferredItem<Item> RAW_PELAGIUM = ITEMS.registerSimpleItem("raw_pelagium");

    /* Food Items */
    public static final DeferredItem<Item> TOAST = ITEMS.registerSimpleItem("toast", p -> p.food(new FoodProperties.Builder().nutrition(8).saturationModifier(1.0F).build()));

    // Tools
    /* Umbralite Suite */
    public static final DeferredItem<Item> UMBRALITE_SWORD = ITEMS.registerItem("umbralite_sword", p -> new Item(p.sword(ScalarToolMaterials.UMBRALITE, 3.0F, -2.4F).fireResistant()));
    public static final DeferredItem<Item> UMBRALITE_PICKAXE = ITEMS.registerItem("umbralite_pickaxe", p -> new Item(p.pickaxe(ScalarToolMaterials.UMBRALITE, 1.0F, -2.8F).fireResistant()));
    public static final DeferredItem<AxeItem> UMBRALITE_AXE = ITEMS.registerItem("umbralite_axe", p -> new AxeItem(ScalarToolMaterials.UMBRALITE, 5.0F, -3.0F, p.fireResistant()));
    public static final DeferredItem<ShovelItem> UMBRALITE_SHOVEL = ITEMS.registerItem("umbralite_shovel", p -> new ShovelItem(ScalarToolMaterials.UMBRALITE, 1.5F, -3.0F, p.fireResistant()));
    public static final DeferredItem<HoeItem> UMBRALITE_HOE = ITEMS.registerItem("umbralite_hoe", p -> new HoeItem(ScalarToolMaterials.UMBRALITE, -4.0F, 0.0F, p.fireResistant()));
    public static final DeferredItem<Item> UMBRALITE_SPEAR = ITEMS.registerItem("umbralite_spear", p -> new Item(p.spear(ScalarToolMaterials.UMBRALITE, 1.15F, 1.2F, 0.4F, 2.5F, 7.0F, 5.5F, 5.1F, 8.75F, 4.6F).fireResistant()));

    /* Saw Items */
    public static final DeferredItem<SawItem> WOODEN_SAW = ITEMS.registerItem("wooden_saw", p -> new SawItem(ToolMaterial.WOOD, -3.2F, p));
    public static final DeferredItem<SawItem> STONE_SAW = ITEMS.registerItem("stone_saw", p -> new SawItem(ToolMaterial.STONE, -3.2F, p));
    public static final DeferredItem<SawItem> IRON_SAW = ITEMS.registerItem("iron_saw", p -> new SawItem(ToolMaterial.IRON, -3.1F, p));
    public static final DeferredItem<SawItem> GOLDEN_SAW = ITEMS.registerItem("golden_saw", p -> new SawItem(ToolMaterial.GOLD, -3.2F, p));
    public static final DeferredItem<SawItem> DIAMOND_SAW = ITEMS.registerItem("diamond_saw", p -> new SawItem(ToolMaterial.DIAMOND, -3.0F, p));
    public static final DeferredItem<SawItem> NETHERITE_SAW = ITEMS.registerItem("netherite_saw", p -> new SawItem(ToolMaterial.NETHERITE, -3.0F, p));
    public static final DeferredItem<SawItem> COPPER_SAW = ITEMS.registerItem("copper_saw", p -> new SawItem(ToolMaterial.COPPER, -3.2F, p));
    public static final DeferredItem<SawItem> UMBRALITE_SAW = ITEMS.registerItem("umbralite_saw", p -> new SawItem(ScalarToolMaterials.UMBRALITE, -3.0F, p.fireResistant()));

    /* Unique Tools */
    public static final DeferredItem<StormHornItem> STORM_HORN = ITEMS.registerItem("storm_horn", StormHornItem::new);
    public static final DeferredItem<EclipseBellItem> ECLIPSE_BELL = ITEMS.registerItem("eclipse_bell", EclipseBellItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
