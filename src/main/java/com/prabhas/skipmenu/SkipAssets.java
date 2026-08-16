package com.prabhas.skipmenu;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SkipAssets {
	public static final String NAMESPACE = "skipmenu";
	public static final Identifier ICON = Identifier.tryParse(NAMESPACE + ":textures/nametag/lunar.png");

	private static final AtomicBoolean loaded = new AtomicBoolean();

	private SkipAssets() {
	}

	public static void reload() {
		loaded.set(false);
		ensure();
	}

	public static void ensure() {
		if (loaded.get()) {
			return;
		}
		Minecraft mc = Minecraft.getInstance();
		if (mc == null || mc.getResourceManager() == null) {
			return;
		}
		TextureManager textures = mc.getTextureManager();
		try {
			NativeImage icon;
			try (InputStream in = mc.getResourceManager().open(ICON)) {
				icon = NativeImage.read(in);
			}
			textures.register(ICON, new DynamicTexture(() -> NAMESPACE + "-icon", icon));
			loaded.set(true);
			SkipMenuClient.LOGGER.info("[SkipMenu] Registered {} texture", ICON);
		} catch (Exception e) {
			loaded.set(false);
			SkipMenuClient.LOGGER.warn("[SkipMenu] Failed to register texture: {}", e.toString());
		}
	}
}