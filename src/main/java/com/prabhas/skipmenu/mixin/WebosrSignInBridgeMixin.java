package com.prabhas.skipmenu.mixin;

import com.prabhas.skipmenu.gui.AccountChoiceScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class WebosrSignInBridgeMixin {
	private static final Logger LOGGER = LoggerFactory.getLogger("skipmenu");

	@Inject(method = "ORIICIHIHHHIOHHHIHRHIOHHIHHHIR", at = @At("HEAD"), cancellable = true)
	private static void showAccountChoice(CallbackInfo ci) {
		ci.cancel();
		Minecraft mc = Minecraft.getInstance();
		mc.execute(() -> {
			Screen current = mc.screen;
			if (current instanceof AccountChoiceScreen) {
				return;
			}
			mc.setScreen(new AccountChoiceScreen());
			LOGGER.info("Login triggered -> showing Offline/Microsoft choice");
		});
	}

	@Invoker("ORIICIHIHHHIOHHHIHRHIOHHIHHHIR")
	public static void skipmenu$invokeSignIn() {
		throw new AssertionError();
	}
}