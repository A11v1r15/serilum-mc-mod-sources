/*
 * This is the latest source code of Fall Through Slime.
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

package com.natamus.fallthroughslime.neoforge.events;

import com.natamus.fallthroughslime.events.SlimeEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.TickEvent.Phase;
import net.neoforged.neoforge.event.TickEvent.PlayerTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeSlimeEvent {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.level();
		if (level.isClientSide || !e.phase.equals(Phase.START)) {
			return;
		}

		SlimeEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
}