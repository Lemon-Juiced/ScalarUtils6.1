package site.scalarstudios.scalarutils.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import site.scalarstudios.scalarutils.ScalarUtils;

public class ScalarUtilsTags {
    // Armor Tags
    public static final TagKey<Item> REPAIRS_UMBRALITE_ARMOR = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ScalarUtils.MODID, "repairs_umbralite_armor"));

    // Tool Tags
    public static final TagKey<Item> REPAIRS_CASCADIUM_TOOL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ScalarUtils.MODID, "repairs_cascadium_tool"));
    public static final TagKey<Item> REPAIRS_RED_DIAMOND_TOOL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ScalarUtils.MODID, "repairs_red_diamond_tool"));
    public static final TagKey<Item> REPAIRS_UMBRALITE_TOOL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ScalarUtils.MODID, "repairs_umbralite_tool"));

}
