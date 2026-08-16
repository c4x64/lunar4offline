package com.prabhas.skipmenu.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.network.chat.Component;

public class LunarButton extends AbstractButton {
	private final Runnable action;
	private final boolean accent;

	public LunarButton(int x, int y, int width, int height, Component label, Runnable action) {
		this(x, y, width, height, label, action, false);
	}

	public LunarButton(int x, int y, int width, int height, Component label, Runnable action, boolean accent) {
		super(x, y, width, height, label);
		this.action = action;
		this.accent = accent;
	}

	@Override
	public void onPress(net.minecraft.client.input.InputWithModifiers inputWithModifiers) {
		this.action.run();
	}

	@Override
	protected void updateWidgetNarration(net.minecraft.client.gui.narration.NarrationElementOutput narrationElementOutput) {
		this.defaultButtonNarrationText(narrationElementOutput);
	}

	@Override
	protected void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		UiStyle.init();
		boolean hovered = this.isHoveredOrFocused();
		UiStyle.button(guiGraphics, this.getX(), this.getY(), this.getWidth(), this.getHeight(), hovered, this.accent);
		Minecraft mc = Minecraft.getInstance();
		int color = this.active ? (hovered ? 0xFFFFFFFF : 0xFFE7E9F0) : 0xFF6E7488;
		guiGraphics.drawCenteredString(mc.font, this.getMessage(), this.getX() + this.getWidth() / 2, this.getY() + (this.getHeight() - 8) / 2, color);
	}
}