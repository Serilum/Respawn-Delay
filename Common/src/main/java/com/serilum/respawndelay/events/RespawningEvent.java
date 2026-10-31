package com.serilum.respawndelay.events;

import com.natamus.collective.functions.MessageFunctions;
import com.serilum.respawndelay.config.ConfigHandler;
import com.serilum.respawndelay.util.Reference;
import com.serilum.respawndelay.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class RespawningEvent {
	public static HashMap<UUID, Date> deathTimes = new HashMap<>();

	public static void onPlayerTick(ServerLevel serverLevel, ServerPlayer serverPlayer) {
		if (serverPlayer.tickCount % 20 != 0) {
			return;
		}

		if (!serverPlayer.isSpectator()) {
			return;
		}

		Date timeDied = deathTimes.get(serverPlayer.getUUID());
		if (timeDied == null) {
			return;
		}

		int respawnDelay = ConfigHandler.respawnDelayInSeconds;
		if (respawnDelay < 0) {
			return;
		}

		Date now = new Date();

		long ms = (now.getTime()-timeDied.getTime());
		if (ms > respawnDelay * 1000L) {
			Util.respawnPlayer(serverLevel, serverPlayer);
			return;
		}

		int seconds = respawnDelay - (int)(ms/1000);
		String waitingMessage = ConfigHandler.waitingForRespawnMessage;
		waitingMessage = waitingMessage.replaceAll("<seconds_left>", seconds + "");

		MessageFunctions.sendMessage(serverPlayer, waitingMessage, ChatFormatting.GRAY);
	}

	public static boolean onPlayerDeath(ServerPlayer serverPlayer, DamageSource damageSource, float damageAmount) {
		if (ConfigHandler.ignoreAdministratorPlayers) {
			if (serverPlayer.hasPermissions(2)) {
				return true;
			}
		}

		if (ConfigHandler.ignoreCreativePlayers) {
			if (serverPlayer.isCreative()) {
				return true;
			}
		}

		ServerLevel serverLevel = (ServerLevel)serverPlayer.level();

		serverPlayer.setGameMode(GameType.SPECTATOR);
		serverPlayer.setHealth(20);
		serverPlayer.getFoodData().setFoodLevel(20);
		serverPlayer.getFoodData().setSaturation(5F);
		serverPlayer.awardStat(Stats.DEATHS);
		serverPlayer.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_DEATH));
		serverPlayer.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
		serverPlayer.clearFire();
		serverPlayer.removeAllEffects();

		Vec3 pvec = serverPlayer.position();
		if (pvec.y() < ConfigHandler.lowestPossibleYCoordinate) {
			pvec = new Vec3(pvec.x(), ConfigHandler.lowestPossibleYCoordinate, pvec.z());
			serverPlayer.setDeltaMovement(0, 0, 0);
			serverPlayer.teleportTo(pvec.x(), pvec.y(), pvec.z());
		}

		if (!ConfigHandler.keepItemsOnDeath) {
			Collection<ItemEntity> playerdrops = new ArrayList<ItemEntity>();

			Inventory inv = serverPlayer.getInventory();
			for (int i=0; i < 36; i++) {
				ItemStack slot = inv.getItem(i);
				if (!slot.isEmpty()) {
					playerdrops.add(new ItemEntity(serverLevel, pvec.x, pvec.y+1, pvec.z, slot.copy()));
					slot.setCount(0);
				}
			}

			for (ItemStack next : serverPlayer.getAllSlots()) {
				if (!next.isEmpty()) {
					playerdrops.add(new ItemEntity(serverLevel, pvec.x, pvec.y + 1, pvec.z, next.copy()));
					next.setCount(0);
				}
			}

			//ForgeHooks.onLivingDrops((LivingEntity)player, e.getSource(), playerdrops, 0, true);

			if (ConfigHandler.dropItemsOnDeath) {
				for (ItemEntity drop : playerdrops) {
					serverLevel.addFreshEntity(drop);
				}
			}
		}

		Date now = new Date();
		deathTimes.put(serverPlayer.getUUID(), now);

		MessageFunctions.sendMessage(serverPlayer, ConfigHandler.onDeathMessage, ChatFormatting.DARK_RED);
		return false;
	}

	public static void onPlayerLogout(Level level, Player player) {
		if (level.isClientSide) {
			return;
		}

		UUID uuid = player.getUUID();
		if (!deathTimes.containsKey(uuid)) {
			return;
		}

		if (!ConfigHandler.respawnWhenPlayerLogsOut) {
			return;
		}

		deathTimes.remove(uuid);
		player.addTag(Reference.MOD_ID + ".respawn_on_login");
	}

	public static void onPlayerLogin(Level level, Player player) {
		if (level.isClientSide) {
			return;
		}

		boolean loggedOutWhileWaiting = player.removeTag(Reference.MOD_ID + ".respawn_on_login");

		if (!player.isSpectator()) {
			return;
		}

		if (deathTimes.containsKey(player.getUUID())) {
			return;
		}

		if (loggedOutWhileWaiting || ConfigHandler.respawnWhenPlayerLogsIn) {
			Util.respawnPlayer(level, (ServerPlayer)player);
		}
	}
}