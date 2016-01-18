package com.jarlandre.rain.tile.impl;

import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.tile.Tile;

public class WallTile extends Tile {

	public WallTile(Sprite sprite) {
		super(sprite);
		solid = true;
	}
}
