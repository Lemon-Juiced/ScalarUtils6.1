package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class SawItem extends AxeItem {
    private static final float ATTACK_DAMAGE_BONUS = 0.0F;
    private static final int DURABILITY_MULTIPLIER = 5;

    public SawItem(ToolMaterial material, float attackSpeed, Item.Properties properties) {
        super(generateToolMaterial(material), ATTACK_DAMAGE_BONUS, attackSpeed, properties);
    }

    private static ToolMaterial generateToolMaterial(ToolMaterial material) {
        return new ToolMaterial(
                material.incorrectBlocksForDrops(),
                material.durability() * DURABILITY_MULTIPLIER,
                material.speed(),
                material.attackDamageBonus(),
                material.enchantmentValue(),
                material.repairItems()
        );
    }
}

