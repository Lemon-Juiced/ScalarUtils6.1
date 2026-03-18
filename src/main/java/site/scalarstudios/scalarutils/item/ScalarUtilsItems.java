package site.scalarstudios.scalarutils.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.GlueItem;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
