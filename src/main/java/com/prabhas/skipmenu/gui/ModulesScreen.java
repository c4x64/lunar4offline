package com.prabhas.skipmenu.gui;

import com.prabhas.skipmenu.SkipMenuClient;
import com.prabhas.skipmenu.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ModulesScreen extends Screen {
	public ModulesScreen() {
		super(Component.literal("Modules"));
	}

	@Override
	protected void init() {
		UiStyle.init();
		int width = 170;
		int height = 24;
		int cx = this.width / 2 - width;
		int settingsW = 96;
		List<Module> modules = SkipMenuClient.MODULES.getModules();

		for (int i = 0; i < modules.size(); i++) {
			Module module = modules.get(i);
			int baseY = this.height / 2 - modules.size() * 20 + i * 42;
			String label = module.name + "  " + (module.enabled ? "ON" : "OFF");
			this.addRenderableWidget(new LunarButton(
					cx - width / 2, baseY, width, height,
					Component.literal(label),
					() -> {
						module.enabled = !module.enabled;
						SkipMenuClient.persistConfig();
						Minecraft.getInstance().setScreen(new ModulesScreen());
					},
					module.enabled));

			this.addRenderableWidget(new LunarButton(
					cx + width / 2 + 4, baseY, settingsW, height,
					Component.literal("Settings..."),
					() -> Minecraft.getInstance().setScreen(settingsFor(module))));
		}

		this.addRenderableWidget(new LunarButton(
				this.width / 2 - 40, this.height - 30, 80, 20,
				Component.literal("Back"),
				() -> Minecraft.getInstance().setScreen(null)));
	}

	private Screen settingsFor(Module module) {
		if (module.id.equals("nametag")) {
			return new NametagSettingsScreen();
		}
		return new ModulesScreen();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderMenuBackground(guiGraphics);
		UiStyle.init();
		int w = 300;
		int h = Math.max(120, Math.min(this.height - 100, 180));
		UiStyle.panel(guiGraphics, this.width / 2 - w / 2, this.height / 2 - h / 2, w, h);
		guiGraphics.drawCenteredString(this.font, Component.literal("Modules"), this.width / 2, 16, 0xFFFFFFFF);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}