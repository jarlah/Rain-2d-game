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

	public void renderTile(int xp, int yp, Sprite sprite) {
		yp -= yOffset;
		xp -= xOffset;
		for (int y = 0; y < sprite.getSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getSize(); x++) {
				int xa = x + xp;
				if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int colour = sprite.getPixels()[x + y * sprite.getSize()];
				if (colour != 0xffff00ff) {
					pixels[xa + ya * width] = sprite.getPixels()[x + y * sprite.getSize()];
				}
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite, boolean xflip, boolean yflip) {
		yp -= yOffset;
		xp -= xOffset;
		for (int y = 0; y < sprite.getSize(); y++) {
			int ya = y + yp;
			int ys = yflip ? 31 - y : y;
			for (int x = 0; x < sprite.getSize(); x++) {
				int xa = x + xp;
				int xs = xflip ? 31 - x : x;
				if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int colour = sprite.getPixels()[xs + ys * sprite.getSize()];
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
