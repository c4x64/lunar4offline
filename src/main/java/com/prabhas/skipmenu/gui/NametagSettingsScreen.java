package com.prabhas.skipmenu.gui;

import com.prabhas.skipmenu.SkipMenuClient;
import com.prabhas.skipmenu.module.Module;
import com.prabhas.skipmenu.module.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class NametagSettingsScreen extends Screen {
	public NametagSettingsScreen() {
		super(Component.literal("Nametag Settings"));
	}

	@Override
	protected void init() {
		UiStyle.init();
		int width = 220;
		int cx = this.width / 2 - width / 2;
		Module nametag = SkipMenuClient.MODULES.get("nametag");
		if (nametag == null) {
			return;
		}
		int baseY = 48;

		this.addRenderableWidget(toggleButton(
				nametag.getSetting("showIcon"), cx, baseY, width,
				"Show icon (offline player)"));
		this.addRenderableWidget(toggleButton(
				nametag.getSetting("iconAll"), cx, baseY + 30, width,
				"Show icon for every player"));

		int size = nametag.getSetting("iconSize").asInt(12);
		int sx = cx;
		for (String option : new String[]{"8", "12", "16"}) {
			final String opt = option;
			boolean selected = String.valueOf(size).equals(opt);
			this.addRenderableWidget(new LunarButton(
					sx, baseY + 62, 70, 22,
					Component.literal(opt),
					() -> {
						nametag.getSetting("iconSize").setValue(opt);
						SkipMenuClient.persistConfig();
						Minecraft.getInstance().setScreen(new NametagSettingsScreen());
					},
					selected));
			sx += 75;
		}

		this.addRenderableWidget(new LunarButton(
				this.width / 2 - 40, this.height - 30, 80, 20,
				Component.literal("Back"),
				() -> Minecraft.getInstance().setScreen(new ModulesScreen())));
	}

	private LunarButton toggleButton(Setting setting, int x, int y, int width, String name) {
		if (setting == null) {
			return new LunarButton(x, y, width, 22, Component.literal(name), () -> {});
		}
		return new LunarButton(
				x, y, width, 22,
				Component.literal(name + "  " + (setting.asBool() ? "ON" : "OFF")),
				() -> {
					setting.setValue(String.valueOf(!setting.asBool()));
					SkipMenuClient.persistConfig();
					Minecraft.getInstance().setScreen(new NametagSettingsScreen());
				},
				setting.asBool());
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderMenuBackground(guiGraphics);
		UiStyle.init();
		int w = 280;
		int h = Math.max(150, Math.min(this.height - 80, 200));
		UiStyle.panel(guiGraphics, this.width / 2 - w / 2, this.height / 2 - h / 2, w, h);
		guiGraphics.drawCenteredString(this.font, Component.literal("Nametag Settings"), this.width / 2, 16, 0xFFFFFFFF);
		guiGraphics.drawCenteredString(this.font, Component.literal("Icon size"), this.width / 2, this.height / 2 - h / 2 + 120, 0xFF9BA1B3);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}