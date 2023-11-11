/*
 * This is the latest source code of Hoe Tweaks.
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

package com.natamus.hoetweaks.neoforge.events;

import com.natamus.hoetweaks.events.HoeEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeHoeEvent {
	@SubscribeEvent
	public static void onHoeRightClickBlock(PlayerInteractEvent.RightClickBlock e) {
		if (!HoeEvent.onHoeRightClickBlock(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), null)) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onHarvestBreakSpeed(PlayerEvent.BreakSpeed e) {
		Player player = e.getEntity();
		float originalSpeed = e.getOriginalSpeed();
		float newSpeed = HoeEvent.onHarvestBreakSpeed(player.level(), player, originalSpeed, e.getState());

		if (originalSpeed != newSpeed) {
			e.setNewSpeed(newSpeed);
		}
	}
}
