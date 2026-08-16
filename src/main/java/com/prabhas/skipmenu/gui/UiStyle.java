package com.prabhas.skipmenu.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;

public final class UiStyle {
	public static final int TEXT = 0xFFE7E9F0;
	public static final int TEXT_DIM = 0xFF9BA1B3;
	public static final int ACCENT = 0xFF8A7FFF;
	public static final int ACCENT_DARK = 0xFF6F62E8;
	public static final int PANEL_FILL = 0xF0141620;
	public static final int PANEL_BORDER = 0xFF2A2E3D;
	public static final int BTN_FILL = 0xFF232738;
	public static final int BTN_HOVER = 0xFF2E3345;
	public static final int BTN_PRESSED = 0xFF1B1E2B;

	public static final int CORNER = 12;
	private static final int TEX_SIZE = 32;

	private static Identifier panelTex;
	private static Identifier accentTex;
	private static Identifier accentHoverTex;
	private static Identifier btnTex;
	private static Identifier btnHoverTex;

	private UiStyle() {
	}

	public static void init() {
		if (panelTex != null) {
			return;
		}
		Minecraft mc = Minecraft.getInstance();
		if (mc == null || mc.getTextureManager() == null) {
			return;
		}
		TextureManager tm = mc.getTextureManager();
		panelTex = Identifier.tryParse("skipmenu:textures/ui/panel.png");
		accentTex = Identifier.tryParse("skipmenu:textures/ui/accent.png");
		accentHoverTex = Identifier.tryParse("skipmenu:textures/ui/accent_hover.png");
		btnTex = Identifier.tryParse("skipmenu:textures/ui/button.png");
		btnHoverTex = Identifier.tryParse("skipmenu:textures/ui/button_hover.png");

		tm.register(panelTex, new DynamicTexture(() -> "skipmenu-panel", rounded(TEX_SIZE, CORNER, PANEL_FILL, PANEL_BORDER)));
		tm.register(accentTex, new DynamicTexture(() -> "skipmenu-accent", rounded(TEX_SIZE, CORNER, ACCENT, ACCENT_DARK)));
		tm.register(accentHoverTex, new DynamicTexture(() -> "skipmenu-accent-hover", rounded(TEX_SIZE, CORNER, 0xFF9B92FF, ACCENT)));
		tm.register(btnTex, new DynamicTexture(() -> "skipmenu-button", rounded(TEX_SIZE, CORNER, BTN_FILL, PANEL_BORDER)));
		tm.register(btnHoverTex, new DynamicTexture(() -> "skipmenu-button-hover", rounded(TEX_SIZE, CORNER, BTN_HOVER, PANEL_BORDER)));
	}

	private static NativeImage rounded(int size, int radius, int fill, int border) {
		NativeImage img = new NativeImage(size, size, false);
		int r = Math.min(radius, size / 2);
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				int d = cornerDist(x, y, size, r);
				if (d >= 0) {
					float a = Math.min(1.0F, d < 0 ? 1.0F : 1.0F - (float) (d / (double) (r + 1)));
					img.setPixel(x, y, blend(fill, a));
				}
			}
		}
		drawBorder(img, size, r, border);
		return img;
	}

	private static int cornerDist(int x, int y, int size, int r) {
		int cx = x < r ? r : (x >= size - r ? size - r - 1 : x);
		int cy = y < r ? r : (y >= size - r ? size - r - 1 : y);
		int dx = x < r ? r - x - 1 : (x >= size - r ? x - (size - r - 1) : 0);
		int dy = y < r ? r - y - 1 : (y >= size - r ? y - (size - r - 1) : 0);
		if (dx == 0 || dy == 0) {
			return -1;
		}
		int distSq = dx * dx + dy * dy;
		return distSq - r * r;
	}

	private static void drawBorder(NativeImage img, int size, int r, int border) {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				int inside = 0;
				for (int oy = -1; oy <= 1; oy++) {
					for (int ox = -1; ox <= 1; ox++) {
						if (isInside(x + ox, y + oy, size, r)) {
							inside++;
						}
					}
				}
				if (!isInside(x, y, size, r) && inside > 0) {
					img.setPixel(x, y, blend(border, 0.85F));
				}
			}
		}
	}

	private static boolean isInside(int x, int y, int size, int r) {
		if (x < 0 || y < 0 || x >= size || y >= size) {
			return false;
		}
		return cornerDist(x, y, size, r) < 0;
	}

	private static int blend(int color, float alpha) {
		int a = (int) (((color >>> 24) & 0xFF) * alpha);
		int rCol = (color >>> 16) & 0xFF;
		int g = (color >>> 8) & 0xFF;
		int b = color & 0xFF;
		return (a << 24) | (rCol << 16) | (g << 8) | b;
	}

	public static void panel(GuiGraphics g, int x, int y, int w, int h) {
		slice(g, panelTex, x, y, w, h);
	}

	public static void button(GuiGraphics g, int x, int y, int w, int h, boolean hovered, boolean accent) {
		slice(g, accent ? (hovered ? accentHoverTex : accentTex) : (hovered ? btnHoverTex : btnTex), x, y, w, h);
	}

	private static void slice(GuiGraphics g, Identifier tex, int x, int y, int w, int h) {
		int r = CORNER;
		int m = TEX_SIZE - r;
		// corners
		blitTex(g, tex, x, y, 0, 0, r, r, r, r);
		blitTex(g, tex, x + w - r, y, TEX_SIZE - r, 0, r, r, r, r);
		blitTex(g, tex, x, y + h - r, 0, TEX_SIZE - r, r, r, r, r);
		blitTex(g, tex, x + w - r, y + h - r, TEX_SIZE - r, TEX_SIZE - r, r, r, r, r);
		// edges
		if (w > 2 * r) {
			blitTex(g, tex, x + r, y, r, 0, w - 2 * r, r, 1, r);
			blitTex(g, tex, x + r, y + h - r, r, TEX_SIZE - r, w - 2 * r, r, 1, r);
		}
		if (h > 2 * r) {
			blitTex(g, tex, x, y + r, 0, r, r, h - 2 * r, r, 1);
			blitTex(g, tex, x + w - r, y + r, TEX_SIZE - r, r, r, h - 2 * r, r, 1);
		}
		// center
		if (w > 2 * r && h > 2 * r) {
			blitTex(g, tex, x + r, y + r, r, r, w - 2 * r, h - 2 * r, 1, 1);
		}
	}

	private static void blitTex(GuiGraphics g, Identifier tex, int x, int y, int u, int v, int w, int h, int uw, int vh) {
		g.blit(RenderPipelines.GUI_TEXTURED, tex, x, y, u, v, w, h, uw, vh, TEX_SIZE, TEX_SIZE, 0);
	}
}
