package com.prabhas.skipmenu.gui;

import com.prabhas.skipmenu.OfflineLogin;
import com.prabhas.skipmenu.SkipMenuClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class OfflineLoginScreen extends Screen {
	private static final int MAX_NAME_LENGTH = 16;

	private EditBox usernameBox;

	public OfflineLoginScreen() {
		super(Component.literal("Offline Login"));
	}

	@Override
	protected void init() {
		this.usernameBox = new EditBox(this.font, this.width / 2 - 100, this.height / 2 - 26, 200, 20, Component.literal("Username"));
		this.usernameBox.setMaxLength(MAX_NAME_LENGTH);
		this.usernameBox.setValue(OfflineLogin.getUsername());
		this.addRenderableWidget(this.usernameBox);

		this.addRenderableWidget(Button.builder(
				Component.literal("Log In"),
				button -> {
					String name = this.usernameBox.getValue().trim();
					OfflineLogin.apply(name);
					SkipMenuClient.CONFIG.offlineUsername = OfflineLogin.getUsername();
					SkipMenuClient.CONFIG.save();
					SkipMenuClient.LOGGER.info("Applied offline account '{}'", OfflineLogin.getUsername());
					Minecraft.getInstance().setScreen(null);
				}
			).bounds(this.width / 2 - 100, this.height / 2 + 6, 200, 20).build());

		this.addRenderableWidget(Button.builder(
				Component.literal("Back"),
				button -> Minecraft.getInstance().setScreen(new AccountChoiceScreen())
			).bounds(this.width / 2 - 100, this.height / 2 + 30, 200, 20).build());
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderMenuBackground(guiGraphics);
		guiGraphics.drawCenteredString(this.font, Component.literal("Offline Login"), this.width / 2, this.height / 2 - 62, 0xFFFFFFFF);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}