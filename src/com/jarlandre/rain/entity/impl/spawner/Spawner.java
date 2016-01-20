package com.jarlandre.rain.entity.impl.spawner;

import java.util.Random;

import com.jarlandre.rain.level.Level;


public abstract class Spawner {
	
	protected double x, y;
	protected Level level;
	protected Random random;
	
	public Spawner(double x, double y) {
		this.x = x;
		this.y = y;
		this.random = new Random();
	}

	public abstract void spawn();
}
