/*
 * This is the latest source code of Tree Harvester.
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

package com.natamus.treeharvester.processing;

import com.natamus.collective.functions.BlockPosFunctions;
import com.natamus.treeharvester.data.Variables;
import com.natamus.treeharvester.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LeafProcessing {
    public static void breakTreeLeaves(Level level, List<BlockPos> logsToBreak, BlockPos lowestCenterLogPos, BlockPos highestLogPos) {
        if (level.isClientSide) {
            return;
        }

        int highestX = Integer.MIN_VALUE; int highestZ = Integer.MIN_VALUE;
        int lowestX = Integer.MAX_VALUE; int lowestZ = Integer.MAX_VALUE;

        for (BlockPos brokenLogPos : logsToBreak) {
            int x = brokenLogPos.getX();
            int z = brokenLogPos.getZ();

            if (x > highestX) {
                highestX = x;
            }
            if (x < lowestX) {
                lowestX = x;
            }
            if (z > highestZ) {
                highestZ = z;
            }
            if (z < lowestZ) {
                lowestZ = z;
            }
        }

        if (!Variables.processBreakLeaves.containsKey(level)) {
            Variables.processBreakLeaves.put(level, new CopyOnWriteArrayList<BlockPos>());
        }

        BlockPos highestLeafPos = highestLogPos.above().immutable();
        Block highestLeafBlock = level.getBlockState(highestLeafPos).getBlock();
        if (!Util.isTreeLeaf(highestLeafBlock)) {
            for (BlockPos aroundPos : BlockPosFunctions.getBlocksAround(highestLeafPos, false)) {
                Block aroundBlock = level.getBlockState(aroundPos).getBlock();
                if (Util.isTreeLeaf(aroundBlock)) {
                    highestLeafBlock = aroundBlock;
                    break;
                }
            }
        }

        boolean highestLeafIsMushroom = Util.isGiantMushroomLeafBlock(highestLeafBlock);

        int distance = 3;
        if (Util.isNetherTreeLeaf(highestLeafBlock)) {
            distance = 5;
        }

        List<BlockPos> treeBlocks = new ArrayList<BlockPos>();
        for (BlockPos iPos : BlockPos.betweenClosed(lowestX-distance, lowestCenterLogPos.getY()-1, lowestZ-distance, highestX+distance, highestLogPos.getY()+5, highestZ+distance)) {
            treeBlocks.add(iPos.immutable());
        }
        Collections.shuffle(treeBlocks);

        for (BlockPos treeBlockPos : treeBlocks) {
            if (!level.isLoaded(treeBlockPos)) {
                continue;
            }

            Block treeBlock = level.getBlockState(treeBlockPos).getBlock();

            if (!Util.isTreeLeaf(treeBlock)) {
                continue;
            }

            if (!highestLeafBlock.equals(treeBlock) && !Util.isNetherTreeLeaf(highestLeafBlock)) {
                continue;
            }

            if ((!Util.isGiantMushroomLeafBlock(treeBlock) && !highestLeafIsMushroom)) {
                if (hasLogsWithinDistance(level, treeBlockPos, 2)) {
                    continue;
                }
            }

            if (!Variables.processBreakLeaves.get(level).contains(treeBlockPos)) {
                Variables.processBreakLeaves.get(level).add(treeBlockPos);
            }
        }
    }

    private static boolean hasLogsWithinDistance(Level level, BlockPos checkPos, int distance) {
        for (BlockPos treePos : BlockPos.betweenClosed(checkPos.getX()-distance, checkPos.getY()-distance, checkPos.getZ()-distance, checkPos.getX()+distance, checkPos.getY()+distance, checkPos.getZ()+distance)) {
            if (Util.isTreeLog(level.getBlockState(treePos).getBlock())) {
                return true;
            }
        }
        return false;
    }
}
