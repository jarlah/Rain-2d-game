package com.jarlandre.rain.entity.impl.particle;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.tile.Tile;

public class Particle extends Entity {
	private int life;
	private int time;
	protected double xa, ya, za;
	protected double zCurrent;
	
	public Particle(double x, double y, int life) {
		this.xCurrent = x;
		this.yCurrent = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zCurrent = random.nextFloat() + 2.0;
	}
	
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) {
			remove();
		}
		za -= 0.1;
		
		if (zCurrent < 0) {
			zCurrent = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move(xCurrent + ya, (yCurrent + ya) + (zCurrent + za));
	}
	
	public void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xCurrent += xa;
		this.yCurrent += ya;
		this.zCurrent += za;
	}
	
	public boolean collision(double x, double y) {
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * Tile.TILE_SIZE) / Tile.TILE_SIZE;
			double yt = (y - c / 2 * Tile.TILE_SIZE) / Tile.TILE_SIZE;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Screen render) {
		render.renderSprite((int) xCurrent - 1, (int) yCurrent - (int) zCurrent, sprite, false, false, false);
	}
}
