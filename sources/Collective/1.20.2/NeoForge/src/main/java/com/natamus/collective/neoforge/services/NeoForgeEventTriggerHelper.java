/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.neoforge.services;

import com.natamus.collective.services.helpers.EventTriggerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

public class NeoForgeEventTriggerHelper implements EventTriggerHelper {
    @Override
    public void triggerNetherPortalSpawnEvent(Level level, BlockPos portalPos, PortalShape size) {
	        NeoForge.EVENT_BUS.post(new BlockEvent.PortalSpawnEvent(level, portalPos, level.getBlockState(portalPos), size));
    }
}