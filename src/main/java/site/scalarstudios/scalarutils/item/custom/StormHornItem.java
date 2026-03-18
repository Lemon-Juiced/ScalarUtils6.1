package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class StormHornItem extends Item {
    private static final int COOLDOWN_TICKS = 600;
    private static final int CLEAR_WEATHER_TICKS = 12000;
    private static final int WEATHER_TICKS = 12000;

    public StormHornItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        ItemStack heldItem = player.getItemInHand(usedHand);

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        if (serverLevel.dimension() != Level.OVERWORLD) {
            player.displayClientMessage(Component.translatable("message.scalarutils.storm_horn.invalid_dimension"), true);
            return InteractionResult.FAIL;
        }

        if (player.getCooldowns().isOnCooldown(heldItem)) {
            player.displayClientMessage(Component.translatable("message.scalarutils.storm_horn.cooldown"), true);
            return InteractionResult.FAIL;
        }

        Holder.Reference<SoundEvent> soundEvent;
        if (player.isShiftKeyDown()) {
            serverLevel.setWeatherParameters(0, WEATHER_TICKS, true, true);
            player.displayClientMessage(Component.translatable("message.scalarutils.storm_horn.thunder"), true);
            soundEvent = SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2);
        } else if (serverLevel.isRaining() || serverLevel.isThundering()) {
            serverLevel.setWeatherParameters(CLEAR_WEATHER_TICKS, 0, false, false);
            player.displayClientMessage(Component.translatable("message.scalarutils.storm_horn.clear"), true);
            soundEvent = SoundEvents.GOAT_HORN_SOUND_VARIANTS.getFirst();
        } else {
            serverLevel.setWeatherParameters(0, WEATHER_TICKS, true, false);
            player.displayClientMessage(Component.translatable("message.scalarutils.storm_horn.rain"), true);
            soundEvent = SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(1);
        }

        serverLevel.playSound(null, player.blockPosition(), soundEvent.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
        player.getCooldowns().addCooldown(heldItem, COOLDOWN_TICKS);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("tooltip.scalarutils.storm_horn.primary").withStyle(ChatFormatting.AQUA));
        tooltipAdder.accept(Component.translatable("tooltip.scalarutils.storm_horn.secondary").withStyle(ChatFormatting.GRAY));
    }
}
