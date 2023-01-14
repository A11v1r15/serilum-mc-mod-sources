/*
 * This is the latest source code of Cycle Paintings.
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

package com.natamus.cyclepaintings.util;

import com.natamus.cyclepaintings.config.ConfigHandler;
import com.natamus.cyclepaintings.data.Constants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.ArrayList;
import java.util.List;

public class Util {
	private static final List<PaintingVariant> paintingtypes = new ArrayList<PaintingVariant>();
	
	public static void setPaintings(Registry<PaintingVariant> paintingRegistry) {
		if (!paintingtypes.isEmpty()) {
			return;
		}

		String[] allignore = ConfigHandler.ignorePaintingsInCycleResourceLocation.split(",");
		boolean debug = ConfigHandler.showRegisteredPaintingsDebug;
		
		if (debug) {
			Constants.logger.info("[Cycle Paintings Debug] The config option 'showRegisteredPaintingsDebug' has been enabled. Showing paintings during cycle registration.");
		}

		for (ResourceLocation motiverl : paintingRegistry.keySet()) {
			if (motiverl == null) {
				continue;
			}
			
			boolean allowed = true;
			String motiverls = motiverl.toString().toLowerCase();
			for (String toignore : allignore) {
				toignore = toignore.toLowerCase().trim();
				if (toignore.contains(":")) {
					if (motiverls.equals(toignore)) {
						allowed = false;
						break;
					}
				}
				else if (motiverls.split(":")[0].contains(toignore)) {
					allowed = false;
					break;
				}
			}
			
			if (!allowed) {
				if (debug) {
					Constants.logger.info("[Cycle Paintings Debug] " + motiverls + " (ignored)");
				}
			}
			else {
				if (debug) {
					Constants.logger.info("[Cycle Paintings Debug] " + motiverls + " (allowed)");
				}
				
				PaintingVariant motive = paintingRegistry.get(motiverl);
				paintingtypes.add(motive);
			}
		}
	}
	
	public static List<PaintingVariant> getSimilarArt(PaintingVariant currentart) {
		List<PaintingVariant> similarart = new ArrayList<PaintingVariant>();
		int xsize = currentart.getWidth();
		int ysize = currentart.getHeight();
		
		for (PaintingVariant aa : paintingtypes) {
			if (aa.getWidth() == xsize && aa.getHeight() == ysize) {
				similarart.add(aa);
			}
		}
		
		return similarart;
	}
}
