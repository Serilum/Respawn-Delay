package com.natamus.respawndelay.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.respawndelay.events.RespawningEvent;
import com.natamus.respawndelay.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Set;

public class CommandRespawnall {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("respawnall").requires((iCommandSender) -> iCommandSender.hasPermission(2))
			.executes((command) -> {
				CommandSourceStack source = command.getSource();
				
				int amountrespawned = 0;
				Set<Player> spectating_players = RespawningEvent.death_times.keySet();
				for (Player nextplayer : spectating_players) {
					Util.respawnPlayer(nextplayer.level(), (ServerPlayer) nextplayer);
					amountrespawned += 1;
				}
				
				String s = "";
				if (amountrespawned > 1) {
					s = "s";
				}
				
				MessageFunctions.sendMessage(source, "Successfully made " + amountrespawned + " player" + s + " respawn.", ChatFormatting.DARK_GREEN);
				return 1;
			})
		);
	}
}
