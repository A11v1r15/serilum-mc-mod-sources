/*
 * This is the latest source code of Breedable Killer Rabbit.
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

package com.natamus.breedablekillerrabbit.neoforge.events;

import com.natamus.breedablekillerrabbit.events.RabbitEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeRabbitEvent {
	@SubscribeEvent
	public static void onBaby(BabyEntitySpawnEvent e) {
		AgeableMob child = e.getChild();
		Level level = child.level();
		if (level.isClientSide) {
			return;
		}

		Mob parentA = e.getParentA();
		Mob parentB = e.getParentB();
		if (!(parentA instanceof Animal) || !(parentB instanceof Animal)) {
			return;
		}

		RabbitEvent.onBaby((ServerLevel)level, (Animal)parentA, (Animal)parentB, e.getChild());
	}
	
	@SubscribeEvent
	public static void onEntityInteract(PlayerInteractEvent.EntityInteract e) {
		Level world = e.getLevel();
		if (world.isClientSide) {
			return;
		}

		RabbitEvent.onEntityInteract(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
	}
	
	@SubscribeEvent
	public static void onTarget(LivingAttackEvent e) {
		Entity entity = e.getEntity();
		if (!RabbitEvent.onTarget(entity.level(), entity, e.getSource(), e.getAmount())) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void mobSpawn(EntityJoinLevelEvent e) {
		RabbitEvent.mobSpawn(e.getLevel(), e.getEntity());
	}
	
	@SubscribeEvent
	public static void onPlayerDamage(LivingHurtEvent e) {
		Entity entity = e.getEntity();
		RabbitEvent.onPlayerDamage(entity.level(), entity, e.getSource(), e.getAmount());
	}
}
