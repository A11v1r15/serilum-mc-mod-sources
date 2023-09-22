/*
 * This is the latest source code of Move Boats.
 * Minecraft version: 1.20.2.
 *
 * Please don't distribute without permission.
 * For all Minecraft modding projects, feel free to visit my profile page on CurseForge or Modrinth.
 *  CurseForge: https://curseforge.com/members/serilum/projects
 *  Modrinth: https://modrinth.com/user/serilum
 *  Overview: https://serilum.com/
 *
 * If you are feeling generous and would like to support the development of the mods, you can!
 *  https://ricksouth.com/donate contains all the information. <3
 *
 * Thanks for looking at the source code! Hope it's of some use to your project. Happy modding!
 */

package com.natamus.moveboats.forge.events;

import com.natamus.moveboats.events.BoatEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeBoatEvent {
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.level();
		if (level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		BoatEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}

	@SubscribeEvent
	public void onBoatClick(PlayerInteractEvent.EntityInteract e) {
		if (BoatEvent.onBoatClick(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null).equals(InteractionResult.SUCCESS)) {
			e.setCanceled(true);
		}
	}
}
