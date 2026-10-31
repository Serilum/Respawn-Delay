package com.serilum.respawndelay.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.natamus.collective.functions.MessageFunctions;
import com.serilum.respawndelay.events.RespawningEvent;
import com.serilum.respawndelay.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.UUID;

public class CommandRespawnall {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("respawnall").requires((iCommandSender) -> iCommandSender.hasPermission(2))
			.executes((command) -> {
				CommandSourceStack source = command.getSource();

				int amountRespawned = 0;
				for (UUID uuid : new ArrayList<>(RespawningEvent.deathTimes.keySet())) {
					ServerPlayer spectatingPlayer = source.getServer().getPlayerList().getPlayer(uuid);
					if (spectatingPlayer == null) {
						continue;
					}

					Util.respawnPlayer(spectatingPlayer.level(), spectatingPlayer);
					amountRespawned += 1;
				}

				MessageFunctions.sendTranslatableMessage(source, "collective.respawndelay.message.successfullymadeplayer", ChatFormatting.DARK_GREEN, amountRespawned);
				return 1;
			})
		);
	}
}
