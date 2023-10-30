/*
 * This is the latest source code of Flower Mimics.
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

package com.natamus.flowermimics.fabric.mixin;

import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LightningBolt.class, priority = 1001)
public class LightningBoltMixin {
	@Inject(method = "spawnFire(I)V", at = @At(value = "HEAD"), cancellable = true)
	private void spawnFire(int i, CallbackInfo ci) {
		LightningBolt lightningBolt = (LightningBolt)(Object)this;
		if (lightningBolt.getTags().contains("visualonly")) {
			ci.cancel();
		}
	}
}