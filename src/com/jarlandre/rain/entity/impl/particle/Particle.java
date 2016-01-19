package com.jarlandre.rain.entity.impl.particle;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.tile.Tile;

public class Particle extends Entity {
	private int life;
	private int time;
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
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.x += xa;
		this.y += ya;
		this.z += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * Tile.TILE_SIZE) / Tile.TILE_SIZE;
			double yt = (y - c / 2 * Tile.TILE_SIZE) / Tile.TILE_SIZE;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}
	
	public void render(Screen render) {
		render.renderSprite((int) x - 1, (int) y - (int) z - 1, sprite, false, false, false);
	}
}
