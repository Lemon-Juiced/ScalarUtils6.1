package site.scalarstudios.scalarutils.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import site.scalarstudios.scalarutils.ScalarUtils;

public final class ScalarToolMaterials {

    public static final TagKey<Item> REPAIRS_RED_DIAMOND_TOOL_MATERIAL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ScalarUtils.MODID, "repairs_red_diamond_tool_material"));
    public static final TagKey<Item> REPAIRS_UMBRALITE_TOOL_MATERIAL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ScalarUtils.MODID, "repairs_umbralite_tool_material"));

    public static final ToolMaterial RED_DIAMOND =  new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10, REPAIRS_RED_DIAMOND_TOOL_MATERIAL);
    public static final ToolMaterial UMBRALITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8124, 10.0F, 6.0F, 20, REPAIRS_UMBRALITE_TOOL_MATERIAL);
}
