package com.jarlandre.rain.tile;

import com.jarlandre.rain.Coordinate;

public class TileCoordinate extends Coordinate {

	public TileCoordinate(Number x, Number y) {
		super(x.longValue() * Tile.TILE_SIZE, y.longValue() * Tile.TILE_SIZE);
	}

}
