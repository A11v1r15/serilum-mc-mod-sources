/*
 * This is the latest source code of Custom End Credits and Poem.
 * Minecraft version: 1.20.1.
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

package com.natamus.customendcreditsandpoem.forge.events;

import com.natamus.customendcreditsandpoem.events.WorldEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeWorldEvents {
	@SubscribeEvent
	public void onWorldLoad(LevelEvent.Load e) {
		LevelAccessor levelAccessor = e.getLevel();
		if (!(levelAccessor instanceof Level)) {
			return;
		}

		WorldEvents.onWorldLoad((Level)levelAccessor);
	}
}