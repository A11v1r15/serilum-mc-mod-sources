/*
 * This is the latest source code of Random Bone Meal Flowers.
 * Minecraft version: 1.20.1.
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

package com.natamus.randombonemealflowers;


import com.natamus.collective.globalcallbacks.GlobalCropCallback;
import com.natamus.randombonemealflowers.events.FlowerEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModCommon {

	public static void init() {
		loadEvents();
	}

	private static void loadEvents() {
		GlobalCropCallback.ON_BONE_MEAL_APPLY.register((Player player, Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			FlowerEvent.onBonemeal(level, blockPos, blockState, itemStack);
			return true;
		});

		GlobalCropCallback.ON_GENERAL_BONE_MEAL_APPLY.register((Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			FlowerEvent.onBonemeal(level, blockPos, blockState, itemStack);
			return true;
		});
	}
}