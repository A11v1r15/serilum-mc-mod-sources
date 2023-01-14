/*
 * This is the latest source code of Smaller Nether Portals.
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

package com.natamus.smallernetherportals.events;

import com.natamus.smallernetherportals.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PortalEvent {
	private static final Map<String, BlockPos> toposes = new HashMap<String, BlockPos>();
	
	public static boolean onClick(Level world, Player player, InteractionHand hand, BlockPos clickpos, BlockHitResult hitVec) {
		if (world.isClientSide) {
			return true;
		}
		
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem().equals(Items.FLINT_AND_STEEL)) {
			int obsidiancount = 0;
			Iterator<BlockPos> it = BlockPos.betweenClosedStream(clickpos.getX()-3, clickpos.getY()-3, clickpos.getZ()-3, clickpos.getX()+3, clickpos.getY()+3, clickpos.getZ()+3).iterator();
			while (it.hasNext()) {
				BlockPos np = it.next();
				if (world.getBlockState(np).getBlock().equals(Blocks.OBSIDIAN)) {
					obsidiancount+=1;
				}
			}
			
			if (obsidiancount >= 6) {
				new Thread(() -> {
					try  { Thread.sleep( 10 ); }
					catch (InterruptedException ignored)  {}

					BlockPos topos = clickpos;

					boolean foundportal = false;
					for (BlockPos np : BlockPos.betweenClosed(clickpos.getX() - 1, clickpos.getY() - 1, clickpos.getZ() - 1, clickpos.getX() + 1, clickpos.getY() + 1, clickpos.getZ() + 1)) {
						Block bsblock = world.getBlockState(np).getBlock();
						if (bsblock instanceof NetherPortalBlock) {
							foundportal = true;
						} else if (bsblock.equals(Blocks.FIRE)) {
							if (Util.isAir(world.getBlockState(np.below(1)))) {
								topos = np.below(1).immutable();
							} else if (Util.isAir(world.getBlockState(np.below(2)))) {
								topos = np.below(2).immutable();
							} else {
								topos = np.immutable();
							}
						}
					}

					if (!foundportal) {
						if (Util.isAir(world.getBlockState(topos))) {
							Util.processSmallerPortal(world, topos.immutable());
						}
					}
				}).start();
			}
		}
		
		return true;
	}
	
	public static void onDimensionChange(ServerLevel world, ServerPlayer player) {
		BlockPos pos = player.blockPosition();
		Block block = world.getBlockState(pos).getBlock();
		
		if (block instanceof NetherPortalBlock) {
			return;
		}
		
		String playername = player.getName().getString();
		
		if (!toposes.containsKey(playername)) {
			BlockPos foundpos = Util.findPortalAround(world, pos);
			if (foundpos != null) {
				List<BlockPos> frontblocks = Util.getFrontBlocks(world, foundpos);
				Util.setObsidian(world, frontblocks);
				
				toposes.put(playername, frontblocks.get(frontblocks.size()-1).above().immutable());
			}
		}
	}
	
	public static void onPlayerTick(ServerLevel world, ServerPlayer player) {
		String playername = player.getName().getString();
		if (!toposes.containsKey(playername)) {
			return;
		}
		
		BlockPos topos = toposes.get(playername); 
		
		player.setPortalCooldown(); // reset time until portal
		player.teleportTo(((double)topos.getX())+0.5, topos.getY(), ((double)topos.getZ())+0.5);
		toposes.remove(playername);
	}
}
