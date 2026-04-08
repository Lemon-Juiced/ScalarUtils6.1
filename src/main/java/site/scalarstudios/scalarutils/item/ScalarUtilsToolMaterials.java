package site.scalarstudios.scalarutils.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import static site.scalarstudios.scalarutils.item.ScalarUtilsTags.REPAIRS_CASCADIUM_TOOL;
import static site.scalarstudios.scalarutils.item.ScalarUtilsTags.REPAIRS_RED_DIAMOND_TOOL;
import static site.scalarstudios.scalarutils.item.ScalarUtilsTags.REPAIRS_UMBRALITE_TOOL;

public final class ScalarUtilsToolMaterials {
    public static final ToolMaterial CASCADIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2031, 9.0F, 3.0F, 14, REPAIRS_CASCADIUM_TOOL);
    public static final ToolMaterial RED_DIAMOND =  new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10, REPAIRS_RED_DIAMOND_TOOL);
    public static final ToolMaterial UMBRALITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8124, 10.0F, 6.0F, 20, REPAIRS_UMBRALITE_TOOL);
}
