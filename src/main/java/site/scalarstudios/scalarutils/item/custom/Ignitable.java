package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

public interface Ignitable {
    float IGNITE_SECONDS = 5.0F;

    default void igniteTarget(LivingEntity target) {
        target.igniteForSeconds(IGNITE_SECONDS);
    }

    default void addIgnitionTooltip(Consumer<Component> tooltipAdder) {
        tooltipAdder.accept(Component.translatable("tooltip.scalarutils.ignition_item").withStyle(ChatFormatting.RED));
    }
}


