/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.mixin;

import com.natamus.collective.fabric.callbacks.CollectiveChatEvents;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.Instant;

@Mixin(value = ChatListener.class, priority = 1001)
public abstract class ChatListenerMixin {
    @Unique ChatType.Bound collective$bound;
    @Unique PlayerInfo collective$playerInfo;

    @Inject(method = "showMessageToPlayer", at = @At("HEAD"))
    public void captureParams(ChatType.Bound bound, PlayerChatMessage playerChatMessage, Component component, PlayerInfo playerInfo, boolean bl, Instant instant, CallbackInfoReturnable<Boolean> cir) {
        collective$bound = bound;
        collective$playerInfo = playerInfo;
    }

    @ModifyVariable(method = "showMessageToPlayer", at = @At("HEAD"), argsOnly = true)
    public Component modifyMessage(Component component) {
        try {
            return CollectiveChatEvents.CLIENT_CHAT_RECEIVED.invoker().onClientChat(collective$bound.chatType(), component, (collective$playerInfo != null ? collective$playerInfo.getProfile().getId() : null));
        } finally {
            collective$bound = null;
            collective$playerInfo = null;
        }
    }
}