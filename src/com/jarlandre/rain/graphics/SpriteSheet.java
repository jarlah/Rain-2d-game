package com.jarlandre.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	public static final SpriteSheet TILES = new SpriteSheet("textures/spritesheet.png", 256);
	
	private final String path;
	private final int size;
	private final int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.size = size;
		this.pixels = new int[size * size];
		load();
	}
	
	private void load() {
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

	public int getSize() {
		return size;
	}

	public int[] getPixels() {
		return pixels;
	}
}
