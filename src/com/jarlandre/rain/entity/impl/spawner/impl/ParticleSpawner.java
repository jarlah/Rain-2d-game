package com.jarlandre.rain.entity.impl.spawner.impl;

import com.jarlandre.rain.entity.impl.particle.Particle;
import com.jarlandre.rain.entity.impl.spawner.Spawner;
import com.jarlandre.rain.level.Level;

public class ParticleSpawner extends Spawner {

	private final int life, amount;

	public ParticleSpawner(double x, double y, int life, int amount, Level level) {
		super(x, y);
		this.level = level;
		this.life = life;
		this.amount = amount;
	}
	
	public void spawn() {
		for (int i = 0; i < amount; i++) {
			Particle p = new Particle(x, y, life);
			level.add(p);
		}
	}
}
