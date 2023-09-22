/*
 * This is the latest source code of Fish On The Line.
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

package com.natamus.fishontheline.events;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.EntityFunctions;
import com.natamus.fishontheline.config.ConfigHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;

public class FishOnTheLineEvent {
	private static final HashMap<String, Integer> sounddelay = new HashMap<String, Integer>();
	
	public static void onPlayerTick(ServerLevel serverLevel, ServerPlayer player) {
		FishingHook fbe = player.fishing;
		if (fbe == null) {
			return;
		}
		
		if (ConfigHandler.mustHoldBellInOffhand) {
			ItemStack offhandStack = player.getOffhandItem();
			Item offhandItem = offhandStack.getItem();
			if (!offhandStack.getItem().equals(Items.BELL)) {
				if (offhandItem instanceof FishingRodItem) {
					if (!player.getMainHandItem().getItem().equals(Items.BELL)) {
						return;
					}
				}
				else {
					return;
				}
			}
		}

		boolean fishontheline = EntityFunctions.fishingHookHasCatch(fbe);
		
		if (fishontheline) {
			int delay = 0;
			
			String playername = player.getName().getString();
			if (sounddelay.containsKey(playername)) {
				delay = sounddelay.get(playername);
			}
			
			if (delay == 0) {
				serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 0.5F, 0.4F / (GlobalVariables.random.nextFloat() * 0.4F + 0.8F));
				delay = 7;
			}
			else {
				delay -= 1;
			}
			
			sounddelay.put(playername, delay);
		}
	}
}
