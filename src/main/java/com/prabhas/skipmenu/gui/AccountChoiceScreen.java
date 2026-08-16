package com.prabhas.skipmenu.gui;

import com.prabhas.skipmenu.OfflineLogin;
import com.prabhas.skipmenu.mixin.WebosrSignInBridgeMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AccountChoiceScreen extends Screen {
	public AccountChoiceScreen() {
		super(Component.literal("Profile"));
	}

	@Override
	protected void init() {
		UiStyle.init();
		int width = 200;
		int height = 24;
		int cx = this.width / 2 - width / 2;
		int baseY = this.height / 2 - 82;

		this.addRenderableWidget(new LunarButton(
				cx, baseY, width, height,
				Component.literal("Modules..."),
				() -> Minecraft.getInstance().setScreen(new ModulesScreen())));

		this.addRenderableWidget(new LunarButton(
				cx, baseY + 32, width, height,
				Component.literal("Login as Offline"),
				() -> Minecraft.getInstance().setScreen(new OfflineLoginScreen())));

		this.addRenderableWidget(new LunarButton(
				cx, baseY + 64, width, height,
				Component.literal("Login with Microsoft"),
				() -> {
					Minecraft.getInstance().setScreen(null);
					WebosrSignInBridgeMixin.skipmenu$invokeSignIn();
				},
				true));

		this.addRenderableWidget(new LunarButton(
				cx, baseY + 96, width, height,
				Component.literal("Close"),
				() -> Minecraft.getInstance().setScreen(null)));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderMenuBackground(guiGraphics);
		UiStyle.init();
		int w = Math.max(260, Math.min(this.width - 40, 320));
		int h = 240;
		UiStyle.panel(guiGraphics, this.width / 2 - w / 2, this.height / 2 - h / 2, w, h);
		guiGraphics.drawCenteredString(this.font, Component.literal("Profile"), this.width / 2, this.height / 2 - h / 2 + 14, 0xFFFFFFFF);
		guiGraphics.drawCenteredString(
			this.font,
			Component.literal("Logged in as: " + OfflineLogin.getUsername()),
			this.width / 2,
			this.height / 2 - h / 2 + 28,
			0xFF9BA1B3
		);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}