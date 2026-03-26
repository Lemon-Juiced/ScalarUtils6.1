package site.scalarstudios.scalarutils.item;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import site.scalarstudios.scalarutils.item.equipment.ScalarUtilsEquipmentAssets;

import java.util.Map;

import static site.scalarstudios.scalarutils.item.ScalarUtilsTags.REPAIRS_UMBRALITE_ARMOR;

public interface ScalarUtilsArmorMaterials {
    ArmorMaterial UMBRALITE = new ArmorMaterial(48, makeDefense(4, 8, 10, 4, 25), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.2F, REPAIRS_UMBRALITE_ARMOR, ScalarUtilsEquipmentAssets.UMBRALITE);

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
