package com.jarlandre.rain.entity.impl.particle;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;

public class Particle extends Entity {
	protected int life;
	protected int time;
	protected double xa, ya, za;
	protected double z;
	
	public Particle(double x, double y, int life) {
		this.x = x;
		this.y = y;
		this.z = 0;
		this.z = random.nextFloat() + 2.0;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;
	}
	
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) {
			remove();
		}
		
		za -= 0.1;
		if (z < 0) {
			z = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move(x + xa, (y + ya) + (z + za));
	}
	
	public void move(double x, double y) {
		if (level.pixelCollision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.x += xa;
		this.y += ya;
		this.z += za;
	}
	
	public void render(Screen render) {
		render.renderSprite((int) x, (int) y - (int) z - 1, sprite, false, false, false);
	}
}
