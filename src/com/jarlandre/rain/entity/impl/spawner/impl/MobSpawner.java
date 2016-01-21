package com.jarlandre.rain.entity.impl.spawner.impl;

import com.jarlandre.rain.entity.impl.mob.impl.Star;
import com.jarlandre.rain.entity.impl.spawner.Spawner;
import com.jarlandre.rain.level.Level;

public class MobSpawner extends Spawner {

	private int amount;

	public MobSpawner(double x, double y, int amount, Level level) {
		super(x, y);
		this.level = level;
		this.amount = amount;
	}

	@Override
	public void spawn() {
		for (int i = 0; i < amount; i++) {
			level.add(new Star(x + random.nextInt(150) + i, y + random.nextInt(50) + i));
		}
	}

}
