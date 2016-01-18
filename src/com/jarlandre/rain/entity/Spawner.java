package com.jarlandre.rain.entity;

import java.util.ArrayList;
import java.util.List;

import com.jarlandre.rain.entity.impl.particle.Particle;
import com.jarlandre.rain.level.Level;

public class Spawner extends Entity{
	private List<Entity> entities = new ArrayList<Entity>();
	
	public enum Type {
		MOB, PARTICLE
	}
	
	public Spawner(double x, double y, Type type, int amount) {
		this.xCurrent = x;
		this.yCurrent = y;
		for (int i = 0; i < amount; i++) {
			switch(type) {
				case PARTICLE:
					entities.add(new Particle(xCurrent, yCurrent, 50));
				case MOB:
					break;
				default:
					break;
			}
		}
	}

	public void spawn(Level level) {
		level.addAll(entities);
	}
}
