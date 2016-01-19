package com.jarlandre.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	public static final SpriteSheet tiles = new SpriteSheet("textures/spritesheet.png", 256);
	public static final SpriteSheet player = new SpriteSheet("textures/player-32x32.png", 128, 128);
	public static final SpriteSheet player_down = new SpriteSheet(player, 0, 0, 4, 1, 32);
	public static final SpriteSheet player_up = new SpriteSheet(player, 0, 1, 4, 1, 32);
	public static final SpriteSheet player_right = new SpriteSheet(player, 0, 3, 4, 1, 32);
	
	private final int width, height;
	private final int[] pixels;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		this.width = w;
		this.height = h;
		pixels = new int[w * h];
		for (int _y = 0; _y < h; _y++) {
			int yp = yy + _y;
			for (int _x = 0; _x < w; _x++) {
				int xp = xx + _x;
				pixels[_x + _y * w] = sheet.pixels[xp + yp * sheet.width];
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		this(path, size, size);
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		load(path);
	}
	
	private void load(String path) {
		try {
			BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public int[] getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
