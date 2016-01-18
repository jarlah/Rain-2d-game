package com.jarlandre.rain.entity.impl.particle;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;

public class Particle extends Entity {
	@SuppressWarnings("unused")
	private int life;
	protected double xa, ya;
	
	public Particle(double x, double y, int life) {
		this.xCurrent = x;
		this.yCurrent = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public void update() {
		this.xCurrent += xa;
		this.yCurrent += ya;
	}
	
	public void render(Screen render) {
		render.renderSprite((int) xCurrent, (int) yCurrent, sprite, false, false, false);
	}
}
