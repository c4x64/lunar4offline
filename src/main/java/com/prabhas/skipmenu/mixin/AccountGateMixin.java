package com.prabhas.skipmenu.mixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public abstract class AccountGateMixin {
	private static final Logger LOGGER = LoggerFactory.getLogger("skipmenu");

	@Inject(method = "RIIHHCOICHHIOHRCHROIOHOOIIHICO", at = @At("HEAD"), cancellable = true)
	private void forceLoggedIn(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(true);
		LOGGER.info("[SkipMenu] Forced Lunar account gate true");
	}
}