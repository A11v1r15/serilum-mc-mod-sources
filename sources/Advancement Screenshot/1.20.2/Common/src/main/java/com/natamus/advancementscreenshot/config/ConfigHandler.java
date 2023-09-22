/*
 * This is the latest source code of Advancement Screenshot.
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

package com.natamus.advancementscreenshot.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.advancementscreenshot.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean showScreenshotTakenMessage = false;
	@Entry(min = 0, max = 100) public static int takeScreenshotTickDelay = 20;

	public static void initConfig() {
		configMetaData.put("showScreenshotTakenMessage", Arrays.asList(
			"If enabled, shows the normal screenshot taken (same as F2) message when a screenshot is taken after an advancement."
		));
		configMetaData.put("takeScreenshotTickDelay", Arrays.asList(
			"How many ticks the mod should wait until it takes a screenshot. By default 1 second. 20 ticks = 1 second"
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}