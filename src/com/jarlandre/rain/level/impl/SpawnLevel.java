package com.jarlandre.rain.level.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jarlandre.rain.entity.impl.spawner.Spawner;
import com.jarlandre.rain.entity.impl.spawner.impl.MobSpawner;
import com.jarlandre.rain.level.Level;
import com.jarlandre.rain.tile.TileCoordinate;

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
		TileCoordinate coord = new TileCoordinate(15, 52);
		Spawner mobSpawner = new MobSpawner(coord.x(), coord.y(), 5);
		mobSpawner.setLevel(this);
		mobSpawner.spawn();
	}
}
