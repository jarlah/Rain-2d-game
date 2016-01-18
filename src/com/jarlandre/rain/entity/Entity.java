package com.jarlandre.rain.entity;

import java.util.Random;

import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.level.Level;

public abstract class Entity {
	
	protected int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {};
	public void render(Screen screen) {};

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		this.removed = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
