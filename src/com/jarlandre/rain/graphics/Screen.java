package com.jarlandre.rain.graphics;


public class Screen {
	private final int width, height;
	private final int[] pixels;
	private int xOffset;
	private int yOffset;

	public Screen(int w, int h) {
		this.width = w;
		this.height = h;
		this.pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed, boolean xflip, boolean yflip) {
		if (!fixed) {
			yp -= yOffset;
			xp -= xOffset;
		}
		for (int y = 0; y < sprite.height(); y++) {
			int ya = y + yp;
			int ys = yflip ? (sprite.height() - 1) - y : y;
			for (int x = 0; x < sprite.width(); x++) {
				int xa = x + xp;
				int xs = xflip ? (sprite.width() - 1) - x : x;
				if (xa < -sprite.width() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int colour = sprite.pixels()[xs + ys * sprite.width()];
				if (colour != 0xffff00ff) {
					pixels[xa + ya * width] = colour;
				}
			}
		}
	}
	
	public void setOffsets(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}
}
