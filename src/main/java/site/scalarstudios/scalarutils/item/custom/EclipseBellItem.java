package site.scalarstudios.scalarutils.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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

public class EclipseBellItem extends Item {
    private static final int COOLDOWN_TICKS = 600;
    private static final long FULL_DAY_TICKS = 24000L;
    private static final long DAY_TARGET = 1000L;
    private static final long NIGHT_TARGET = 13000L;

    public EclipseBellItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        ItemStack heldItem = player.getItemInHand(usedHand);

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        if (serverLevel.dimensionType().hasFixedTime()) {
            player.displayClientMessage(Component.translatable("message.scalarutils.eclipse_bell.invalid_dimension"), true);
            return InteractionResult.FAIL;
        }

        if (player.getCooldowns().isOnCooldown(heldItem)) {
            player.displayClientMessage(Component.translatable("message.scalarutils.eclipse_bell.cooldown"), true);
            return InteractionResult.FAIL;
        }

        long dayTime = serverLevel.getDayTime();
        long dayStart = dayTime - Math.floorMod(dayTime, FULL_DAY_TICKS);
        long timeOfDay = Math.floorMod(dayTime, FULL_DAY_TICKS);
        boolean currentlyDay = timeOfDay < 12000L;
        long targetTime = dayStart + (currentlyDay ? NIGHT_TARGET : DAY_TARGET);
        if (targetTime <= dayTime) {
            targetTime += FULL_DAY_TICKS;
        }

        serverLevel.setDayTime(targetTime);
        player.displayClientMessage(Component.translatable(currentlyDay ? "message.scalarutils.eclipse_bell.night" : "message.scalarutils.eclipse_bell.day"), true);

        serverLevel.playSound(null, player.blockPosition(), SoundEvents.BELL_BLOCK, SoundSource.PLAYERS, 1.0F, 1.0F);
        player.getCooldowns().addCooldown(heldItem, COOLDOWN_TICKS);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("tooltip.scalarutils.eclipse_bell.primary").withStyle(ChatFormatting.GOLD));
    }
}

