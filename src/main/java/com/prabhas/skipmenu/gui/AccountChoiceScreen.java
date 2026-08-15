package com.prabhas.skipmenu.gui;

import com.prabhas.skipmenu.mixin.WebosrSignInBridgeMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AccountChoiceScreen extends Screen {
	public AccountChoiceScreen() {
		super(Component.literal("Add Account"));
	}

	@Override
	protected void init() {
		int width = 180;
		int height = 20;
		int cx = this.width / 2 - width / 2;
		int baseY = this.height / 2 - 36;

		this.addRenderableWidget(Button.builder(
				Component.literal("Offline"),
				button -> Minecraft.getInstance().setScreen(new OfflineLoginScreen())
			).bounds(cx, baseY, width, height).build());

		this.addRenderableWidget(Button.builder(
				Component.literal("Microsoft"),
				button -> {
					Minecraft.getInstance().setScreen(null);
					WebosrSignInBridgeMixin.skipmenu$invokeSignIn();
				}
			).bounds(cx, baseY + 28, width, height).build());

		this.addRenderableWidget(Button.builder(
				Component.literal("Cancel"),
				button -> Minecraft.getInstance().setScreen(null)
			).bounds(cx, baseY + 56, width, height).build());
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderMenuBackground(guiGraphics);
		guiGraphics.drawCenteredString(this.font, Component.literal("Add Account"), this.width / 2, this.height / 2 - 62, 0xFFFFFFFF);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}