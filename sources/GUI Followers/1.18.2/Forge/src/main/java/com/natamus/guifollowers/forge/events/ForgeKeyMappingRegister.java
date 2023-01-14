/*
 * This is the latest source code of GUI Followers.
 * Minecraft version: 1.18.2.
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

package com.natamus.guifollowers.forge.events;

import com.natamus.guifollowers.data.Variables;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ForgeKeyMappingRegister {
    public static void initClient(final FMLClientSetupEvent event) {
    	Variables.clearlist_hotkey = new KeyMapping("guifollowers.key.clearlist", 92, "key.categories.misc");
    	ClientRegistry.registerKeyBinding(Variables.clearlist_hotkey);
    }
}