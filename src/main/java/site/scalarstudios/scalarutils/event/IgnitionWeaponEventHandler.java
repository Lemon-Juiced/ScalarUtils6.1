package site.scalarstudios.scalarutils.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.Ignitable;

@EventBusSubscriber(modid = ScalarUtils.MODID)
public class IgnitionWeaponEventHandler {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity target = event.getEntity();
        if (target.level().isClientSide()) {
            return;
        }

        Entity attackerEntity = event.getSource().getEntity();
        if (!(attackerEntity instanceof LivingEntity attacker)) {
            return;
        }

        ItemStack heldItem = attacker.getMainHandItem();
        if (!(heldItem.getItem() instanceof Ignitable ignitionWeapon)) {
            return;
        }

        ignitionWeapon.igniteTarget(target);
    }
}

