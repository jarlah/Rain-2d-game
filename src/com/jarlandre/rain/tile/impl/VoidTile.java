package com.jarlandre.rain.tile.impl;

import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.tile.Tile;

public class VoidTile extends Tile {

	public VoidTile() {
		super(Sprite.voidSprite);
		solid = true;
	}

}
