/*
 * This is the latest source code of Configurable Extra Mob Drops.
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

package com.natamus.configurableextramobdrops;

import com.natamus.collective.check.RegisterMod;
import com.natamus.configurableextramobdrops.neoforge.events.NeoForgeMobDropEvent;
import com.natamus.configurableextramobdrops.util.Reference;
import com.natamus.configurableextramobdrops.util.Util;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class ModNeoForge {
	
	public ModNeoForge() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		try {
			Util.loadMobConfigFile();
		} catch (Exception ex) {
			System.out.println("[" + Reference.NAME + "] Error on loading the entity config file. The mod has been disabled.");
			return;
		}

		NeoForge.EVENT_BUS.register(NeoForgeMobDropEvent.class);
	}

	private static void setGlobalConstants() {

	}
}