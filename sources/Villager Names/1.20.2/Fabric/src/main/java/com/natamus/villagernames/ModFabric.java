/*
 * This is the latest source code of Villager Names.
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

package com.natamus.villagernames;

import com.natamus.collective.check.RegisterMod;
import com.natamus.villagernames.events.VillagerEvent;
import com.natamus.villagernames.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			VillagerEvent.onSpawn(world, entity);
		});

		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			return VillagerEvent.onVillagerInteract(player, world, hand, entity, hitResult);
		});
	}

	private static void setGlobalConstants() {

	}
}
