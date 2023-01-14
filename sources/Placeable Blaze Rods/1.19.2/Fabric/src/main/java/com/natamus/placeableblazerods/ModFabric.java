/*
 * This is the latest source code of Placeable Blaze Rods.
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

package com.natamus.placeableblazerods;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveBlockEvents;
import com.natamus.placeableblazerods.blocks.BlazeRodBlock;
import com.natamus.placeableblazerods.data.Constants;
import com.natamus.placeableblazerods.events.BlazeRodEvent;
import com.natamus.placeableblazerods.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		registerBlocks();
		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void registerBlocks() {
		Constants.BLAZE_ROD_BLOCK = new BlazeRodBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F).lightLevel((p_235454_0_) -> { return 14; }).sound(SoundType.WOOD).noOcclusion());

		Registry.register(Registry.BLOCK, new ResourceLocation(Reference.MOD_ID, "blaze_rod"),  Constants.BLAZE_ROD_BLOCK);
	}

	private void loadEvents() {
		CollectiveBlockEvents.BLOCK_RIGHT_CLICK.register((Level world, Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) -> {
			return BlazeRodEvent.onBlockClick(world, player, hand, pos, hitVec);
		});
	}

	private static void setGlobalConstants() {

	}
}
