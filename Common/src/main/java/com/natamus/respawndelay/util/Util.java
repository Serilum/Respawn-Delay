package com.natamus.respawndelay.util;

import com.natamus.collective.functions.PlayerFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.respawndelay.config.ConfigHandler;
import com.natamus.respawndelay.events.RespawningEvent;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

public class Util {
	public static void respawnPlayer(Level world, ServerPlayer serverplayer) {
		if (!PlayerFunctions.respawnPlayer(world, serverplayer)) {
			return;
		}
		
		RespawningEvent.death_times.remove(serverplayer);
		serverplayer.setGameMode(GameType.SURVIVAL);
		if (ConfigHandler.respawnAtWorldSpawn) {
			BlockPos spawnpos = PlayerFunctions.getSpawnPoint(world, serverplayer);
			serverplayer.teleportTo(spawnpos.getX(), spawnpos.getY(), spawnpos.getZ());
		}
		
		StringFunctions.sendMessage(serverplayer, ConfigHandler.onRespawnMessage, ChatFormatting.DARK_GREEN);
	}
}
