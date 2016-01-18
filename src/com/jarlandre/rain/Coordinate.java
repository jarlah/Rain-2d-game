package com.jarlandre.rain;

public class Coordinate {
	private final int x, y;
	
	public Coordinate(int x, int y, int tileSize) {
		this.x = x * tileSize;
		this.y = y * tileSize;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
