/*
 * This is the latest source code of Tree Harvester.
 * Minecraft version: 1.19.4.
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

package com.natamus.treeharvester.data;

import com.natamus.collective.functions.DataFunctions;

import java.io.File;

public class Constants {
	public static final String dirpath = DataFunctions.getConfigDirectory() + File.separator + "treeharvester";
	public static final File dir = new File(dirpath);
	public static final File file = new File(dirpath + File.separator + "harvestable_axe_blacklist.txt");
}