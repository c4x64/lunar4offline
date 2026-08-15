package com.prabhas.skipmenu.mixin;

import com.prabhas.skipmenu.OfflineLogin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Shadow
	@Final
	@Mutable
	private User user;

	@Shadow
	private static Logger LOGGER;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void handleMinecraftInit(CallbackInfo ci) {
		this.user = OfflineLogin.currentUser();
		LOGGER.info("[SkipMenu] Applied offline user {}", OfflineLogin.getUsername());
	}

	@Inject(method = "getUser", at = @At("HEAD"), cancellable = true)
	private void overrideGetUser(CallbackInfoReturnable<User> cir) {
		cir.setReturnValue(OfflineLogin.currentUser());
	}
}