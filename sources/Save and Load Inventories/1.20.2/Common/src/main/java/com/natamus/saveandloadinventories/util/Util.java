/*
 * This is the latest source code of Save and Load Inventories.
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

package com.natamus.saveandloadinventories.util;

import com.natamus.collective.functions.DataFunctions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
	private static final String dirpath = DataFunctions.getConfigDirectory() + File.separator + "saveandloadinventories";
	
	public static boolean writeGearStringToFile(String filename, String gearstring) {
		File dir = new File(dirpath);
		dir.mkdirs();
		
		try {
			PrintWriter writer = new PrintWriter(dirpath + File.separator + filename + ".txt", StandardCharsets.UTF_8);
			writer.println(gearstring);
			writer.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static String getGearStringFromFile(String filename) {
		File dir = new File(dirpath);
		File file = new File(dirpath + File.separator + filename + ".txt");
		
		String gearstring = "";
		if (dir.isDirectory() && file.isFile()) {
			try {
				gearstring = new String(Files.readAllBytes(Paths.get(dirpath + File.separator + filename + ".txt", new String[0])));
			}
			catch (IOException ignored) { }
		}
		
		return gearstring;
	}
	
	public static String getListOfInventories() {
		StringBuilder inventories = new StringBuilder();
		
		File folder = new File(dirpath);
		if (!folder.isDirectory()) {
			return inventories.toString();
		}
		
		File[] listOfFiles = folder.listFiles();
		for (File listOfFile : listOfFiles) {
			if (listOfFile.isFile()) {
				if (!inventories.toString().equals("")) {
					inventories.append(", ");
				}
				inventories.append(listOfFile.getName().replace(".txt", ""));
			}
		}
		
		return inventories.toString();
	}
}