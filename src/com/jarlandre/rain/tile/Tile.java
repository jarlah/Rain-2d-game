package com.jarlandre.rain.tile;

import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.tile.impl.FloorTile;
import com.jarlandre.rain.tile.impl.GrassTile;
import com.jarlandre.rain.tile.impl.VoidTile;
import com.jarlandre.rain.tile.impl.WallTile;

public class Tile {
	public static final int TILE_SIZE = 16;
	
	public static final Tile grass = new GrassTile(Sprite.grass);
	public static final Tile wall1 = new WallTile(Sprite.wall1);
	public static final Tile wall2 = new WallTile(Sprite.wall2);
	public static final Tile floor = new FloorTile(Sprite.floor);
	
	public static final int grass_colour = 0xff00ff00;
	public static final int wall1_colour = 0xff808080;
	public static final int wall2_colour = 0xff303030;
	public static final int floor_colour = 0xff724715;
	
	public static final Tile voidTile = new VoidTile();
	
	protected int x, y;
	protected Sprite sprite;
	protected boolean solid;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * sprite.getSize(), y * sprite.getSize(), this.sprite);
	}
	
	public boolean solid() {
		return solid;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
