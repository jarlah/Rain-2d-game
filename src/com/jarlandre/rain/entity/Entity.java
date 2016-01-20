package com.jarlandre.rain.entity;

import java.util.Random;

import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.level.Level;

public abstract class Entity {
	protected Sprite sprite;
	protected double x, y;
	protected boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public abstract void update();
	
	public abstract void render(Screen screen);

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		this.removed = true;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return this.level;
	}
}
