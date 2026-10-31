package com.serilum.respawndelay.util;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.functions.PlayerFunctions;
import com.serilum.respawndelay.config.ConfigHandler;
import com.serilum.respawndelay.events.RespawningEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Set;
import java.util.UUID;

public class Util {
	public static void respawnPlayer(Level level, ServerPlayer serverPlayer) {
		UUID uuid = serverPlayer.getUUID();
		ServerLevel spectatingLevel = serverPlayer.level();
		Vec3 spectatingPosition = serverPlayer.position();
		float spectatingYRot = serverPlayer.getYRot();
		float spectatingXRot = serverPlayer.getXRot();

		if (!PlayerFunctions.respawnPlayer(level, serverPlayer)) {
			return;
		}

		RespawningEvent.deathTimes.remove(uuid);

		ServerPlayer respawnedPlayer = level.getServer().getPlayerList().getPlayer(uuid);
		if (respawnedPlayer == null) {
			return;
		}

		respawnedPlayer.setGameMode(GameType.SURVIVAL);
		if (!ConfigHandler.respawnAtWorldSpawn) {
			respawnedPlayer.teleportTo(spectatingLevel, spectatingPosition.x(), spectatingPosition.y(), spectatingPosition.z(), Set.of(), spectatingYRot, spectatingXRot, true);
		}

		MessageFunctions.sendMessage(respawnedPlayer, ConfigHandler.onRespawnMessage, ChatFormatting.DARK_GREEN);
	}

	public static boolean isSavedByTotem(ServerPlayer serverPlayer, DamageSource damageSource) {
		if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
			return false;
		}

		for (InteractionHand hand : InteractionHand.values()) {
			if (serverPlayer.getItemInHand(hand).has(DataComponents.DEATH_PROTECTION)) {
				return true;
			}
		}

		return false;
	}
}
