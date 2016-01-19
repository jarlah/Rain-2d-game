package com.jarlandre.rain.entity.impl.spawner.impl;

import com.jarlandre.rain.entity.impl.mob.impl.Ogre;
import com.jarlandre.rain.entity.impl.spawner.Spawner;

public class MobSpawner extends Spawner {

	private int amount;

	public MobSpawner(double x, double y, int amount) {
		super(x, y);
		this.amount = amount;
	}

	@Override
	public void spawn() {
		for (int i = 0; i < amount; i++) {
			level.add(new Ogre(x + random.nextInt(150) + i, y + random.nextInt(50) + i));
		}
	}

}
