/*
 * This is the latest source code of Scaffolding Drops Nearby.
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

package com.natamus.scaffoldingdropsnearby.neoforge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.scaffoldingdropsnearby.events.ScaffoldingEvent;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeScaffoldingEvent {
	@SubscribeEvent
	public static void onScaffoldingItem(EntityJoinLevelEvent e) {
		ScaffoldingEvent.onScaffoldingItem(e.getLevel(), e.getEntity());
	}
	
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		ScaffoldingEvent.onBlockBreak(level, e.getPlayer(), e.getPos(), e.getState(), null);
	}
}
