package com.jarlandre.rain.level.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jarlandre.rain.level.Level;

public class SpawnLevel extends Level {	
	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.tiles = image.getRGB(0, 0, width, height, null, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
