package com.jarlandre.rain.entity.impl.spawner.impl;

import com.jarlandre.rain.entity.impl.particle.Particle;
import com.jarlandre.rain.entity.impl.spawner.Spawner;

public class ParticleSpawner extends Spawner {

	private final int life;

	public ParticleSpawner(double x, double y, int life, int amount) {
		super(x, y, Type.PARTICLE);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			entities.add(new Particle(x, y, life));
		}
	}

	public int getLife() {
		return life;
	}

}
