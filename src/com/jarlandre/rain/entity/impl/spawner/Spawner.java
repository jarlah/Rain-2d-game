package com.jarlandre.rain.entity.impl.spawner;

import java.util.ArrayList;
import java.util.List;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.level.Level;

public class Spawner extends Entity {
	protected List<Entity> entities = new ArrayList<Entity>();
	
	public enum Type {
		MOB, PARTICLE
	}
	
	private final Type type;
	
	public Spawner(double x, double y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void spawn(Level level) {
		level.addAll(entities);
	}

	public Type getType() {
		return type;
	}
}
