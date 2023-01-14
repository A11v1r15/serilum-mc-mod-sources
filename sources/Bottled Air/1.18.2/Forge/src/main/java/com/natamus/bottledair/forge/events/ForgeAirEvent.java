/*
 * This is the latest source code of Bottled Air.
 * Minecraft version: 1.18.2.
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

package com.natamus.bottledair.forge.events;

import com.natamus.bottledair.events.AirEvent;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeAirEvent {
	@SubscribeEvent
	public void onBottleClick(PlayerInteractEvent.RightClickItem e) {
		if (AirEvent.onBottleClick(e.getPlayer(), e.getWorld(), e.getHand()).getResult().equals(InteractionResult.FAIL)) {
			e.setCanceled(true);
		}
	}
}
