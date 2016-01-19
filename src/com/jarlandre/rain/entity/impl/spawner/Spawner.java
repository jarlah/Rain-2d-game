package com.jarlandre.rain.entity.impl.spawner;

import com.jarlandre.rain.entity.Entity;

public abstract class Spawner extends Entity {		
	public Spawner(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract void spawn();
}
