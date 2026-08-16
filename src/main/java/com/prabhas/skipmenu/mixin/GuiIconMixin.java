package com.prabhas.skipmenu.mixin;

import com.prabhas.skipmenu.OfflineLogin;
import com.prabhas.skipmenu.SkipAssets;
import com.prabhas.skipmenu.SkipMenuClient;
import com.prabhas.skipmenu.module.Module;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiIconMixin {
	@Inject(method = "render", at = @At("HEAD"))
	private void skipmenu$renderIcons(GuiGraphics graphics, DeltaTracker tracker, CallbackInfo ci) {
		Module nametag = SkipMenuClient.MODULES.get("nametag");
		if (nametag == null || !nametag.enabled) {
			return;
		}
		SkipAssets.ensure();
		if (nametag.getSetting("showIcon") == null || !nametag.getSetting("showIcon").asBool()) {
			return;
		}
		Minecraft mc = Minecraft.getInstance();
		if (mc.screen != null || mc.level == null || mc.getCameraEntity() == null) {
			return;
		}
		int width = mc.getWindow().getGuiScaledWidth();
		int height = mc.getWindow().getGuiScaledHeight();
		int size = nametag.getSetting("iconSize").asInt(12);
		boolean all = nametag.getSetting("iconAll").asBool();
		boolean firstPerson = mc.options.getCameraType().isFirstPerson();

		for (Player player : mc.level.players()) {
			if (player == mc.getCameraEntity() && firstPerson) {
				continue;
			}
			if (!all && !OfflineLogin.isLocalProfile(player.getGameProfile())) {
				continue;
			}
			double distanceSq = player.distanceToSqr(mc.getCameraEntity());
			if (distanceSq > 4096.0) {
				continue;
			}
			Vec3 world = player.getEyePosition(1.0F);
			Vec3 projected = mc.gameRenderer.projectPointToScreen(world);
			if (projected.z <= 0.0 || projected.z >= 1.0) {
				continue;
			}
			int x = (int)((projected.x + 1.0) * 0.5 * width);
			int y = (int)((1.0 - projected.y) * 0.5 * height);
			if (x < -48 || y < -48 || x > width + 48 || y > height + 48) {
				continue;
			}
			int left = x - size / 2;
			int top = y - size / 2 - size;
			graphics.blit(SkipAssets.ICON, left, top, left + size, top + size, 0.0F, 0.0F, 1.0F, 1.0F);
		}
	}
}