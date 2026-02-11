package com.ashwake.armourweapons;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = AshwakeArmourWeapons.MODID)
public final class WoodenElytraMovementEvents {
    private static final ElytraTier WOODEN_TIER = new ElytraTier(-0.6D, 2.0D, 2.0D);
    private static final ElytraTier IRON_TIER = new ElytraTier(-0.5D, 1.5D, 2.5D);
    private static final ElytraTier GOLDEN_TIER = new ElytraTier(-0.4D, 1.0D, 3.0D);
    private static final ElytraTier NETHERITE_TIER = new ElytraTier(-0.3D, 0.8D, 4.0D);
    private static final Map<UUID, WoodenElytraState> STATES = new HashMap<>();

    private WoodenElytraMovementEvents() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        WoodenElytraState state = STATES.computeIfAbsent(serverPlayer.getUUID(), id -> new WoodenElytraState());
        boolean jumpPressed = serverPlayer.getLastClientInput().jump();

        ElytraTier tier = getElytraTier(player);
        if (tier == null) {
            state.reset(jumpPressed);
            return;
        }

        if (player.onGround()) {
            state.doubleJumpAvailable = true;
            state.noFallDamageUntilGround = false;
        }

        if (shouldDoubleJump(player, state, jumpPressed)) {
            player.jumpFromGround();
            state.doubleJumpAvailable = false;
            state.noFallDamageUntilGround = true;
            player.resetFallDistance();
            player.hurtMarked = true;
        }

        applyGlide(player, state, tier);
        state.lastJumpPressed = jumpPressed;
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        STATES.remove(event.getEntity().getUUID());
    }

    private static ElytraTier getElytraTier(Player player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.is(AshwakeArmourWeapons.WOODEN_ELYTRA.get())) {
            return WOODEN_TIER;
        }
        if (chest.is(AshwakeArmourWeapons.IRON_ELYTRA.get())) {
            return IRON_TIER;
        }
        if (chest.is(AshwakeArmourWeapons.GOLDEN_ELYTRA.get())) {
            return GOLDEN_TIER;
        }
        if (chest.is(AshwakeArmourWeapons.NETHERITE_ELYTRA.get())) {
            return NETHERITE_TIER;
        }
        return null;
    }

    private static boolean shouldDoubleJump(Player player, WoodenElytraState state, boolean jumpPressed) {
        if (player.onGround() || !state.doubleJumpAvailable) {
            return false;
        }
        if (player.getAbilities().flying || player.isSpectator()) {
            return false;
        }
        if (player.isInWater() || player.isInLava() || player.onClimbable() || player.isPassenger()) {
            return false;
        }
        return jumpPressed && !state.lastJumpPressed;
    }

    private static void applyGlide(Player player, WoodenElytraState state, ElytraTier tier) {
        if (player.onGround()) {
            return;
        }
        if (player.getAbilities().flying || player.isSpectator()) {
            return;
        }
        if (player.isInWater() || player.isInLava() || player.onClimbable() || player.isPassenger()) {
            return;
        }

        if (player.fallDistance < tier.glideMinFallDistance) {
            return;
        }

        Vec3 motion = player.getDeltaMovement();
        if (motion.y < tier.glideFallSpeed) {
            player.setDeltaMovement(motion.x, tier.glideFallSpeed, motion.z);
            player.hurtMarked = true;
        }

        double safeFallDistance = player.getAttributeValue(Attributes.SAFE_FALL_DISTANCE);
        double extraFallDistance = state.noFallDamageUntilGround ? 0.0D : tier.glideMaxExtraFallDistance;
        double maxFallDistance = safeFallDistance + extraFallDistance;
        if (player.fallDistance > maxFallDistance) {
            player.fallDistance = maxFallDistance;
        }
    }

    private record ElytraTier(double glideFallSpeed, double glideMinFallDistance, double glideMaxExtraFallDistance) {
    }

    private static final class WoodenElytraState {
        private boolean lastJumpPressed;
        private boolean doubleJumpAvailable;
        private boolean noFallDamageUntilGround;

        private void reset(boolean jumpPressed) {
            lastJumpPressed = jumpPressed;
            doubleJumpAvailable = false;
            noFallDamageUntilGround = false;
        }
    }
}
