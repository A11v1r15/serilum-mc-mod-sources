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

package com.natamus.collective.fabric.services;

import com.natamus.collective.fabric.callbacks.CollectiveBlockEvents;
import com.natamus.collective.services.helpers.EventTriggerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalShape;

public class FabricEventTriggerHelper implements EventTriggerHelper {
    @Override
    public void triggerNetherPortalSpawnEvent(Level level, BlockPos portalPos, PortalShape size) {
        CollectiveBlockEvents.ON_NETHER_PORTAL_SPAWN.invoker().onPossiblePortal(level, portalPos, size);
    }
}