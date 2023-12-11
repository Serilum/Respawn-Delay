package com.natamus.respawndelay.forge.events;

import com.natamus.respawndelay.cmds.CommandRespawnall;
import com.natamus.respawndelay.events.RespawningEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeRespawningEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandRespawnall.register(e.getDispatcher());
    }

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.getCommandSenderWorld();
		if (level.isClientSide || e.phase != Phase.START) {
			return;
		}

		RespawningEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}

	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level level = entity.getCommandSenderWorld();
		if (level.isClientSide) {
			return;
		}

		if (!(entity instanceof Player)) {
			return;
		}

		if (!RespawningEvent.onPlayerDeath((ServerPlayer)entity, e.getSource(), 0)) {
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		Player player = e.getEntity();
		RespawningEvent.onPlayerLogout(player.level, player);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getEntity();
		RespawningEvent.onPlayerLogin(player.level, player);
	}
}