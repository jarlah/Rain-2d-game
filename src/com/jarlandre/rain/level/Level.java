package com.jarlandre.rain.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.level.impl.SpawnLevel;
import com.jarlandre.rain.tile.Tile;

public abstract class Level {
	protected int width, height;
	protected int[] tiles;
	private List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());

	public static final Level level1 = new SpawnLevel("textures/spawn_level.png");

	public Level(String path) {
		loadLevel(path);
	}

	protected void loadLevel(String path) {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);	
			if (e.isRemoved()) {
				entities.remove(e);
			} else {
				e.update();
			}
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffsets(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.getWidth() + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == Tile.grass_colour)
			return Tile.grass;
		if (tiles[x + y * width] == Tile.wall1_colour)
			return Tile.wall1;
		if (tiles[x + y * width] == Tile.wall2_colour)
			return Tile.wall2;
		if (tiles[x + y * width] == Tile.floor_colour)
			return Tile.floor;
		return Tile.voidTile;
	}

	public void add(Entity bullet) {
		bullet.setLevel(this);
		entities.add(bullet);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
}
