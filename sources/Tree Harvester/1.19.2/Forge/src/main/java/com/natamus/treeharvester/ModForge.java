/*
 * This is the latest source code of Tree Harvester.
 * Minecraft version: 1.19.2.
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

package com.natamus.treeharvester;

import com.natamus.collective.check.RegisterMod;
import com.natamus.treeharvester.forge.config.IntegrateForgeConfig;
import com.natamus.treeharvester.forge.events.*;
import com.natamus.treeharvester.util.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Reference.MOD_ID)
public class ModForge {
	
	public ModForge() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();

		IntegrateForgeConfig.registerScreen(ModLoadingContext.get());

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		MinecraftForge.EVENT_BUS.register(new ForgeLeafEvents());
		MinecraftForge.EVENT_BUS.register(new ForgeSaplingEvents());
        MinecraftForge.EVENT_BUS.register(new ForgeTreeCutEvents());
		MinecraftForge.EVENT_BUS.register(new ForgeWorldEvents());

		if (FMLEnvironment.dist.equals(Dist.CLIENT)) {
			MinecraftForge.EVENT_BUS.register(new ForgeSoundEvents());
		}
	}

	private static void setGlobalConstants() {

	}
}