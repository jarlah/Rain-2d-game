package com.jarlandre.rain.entity.impl.projectiles.impl;

import com.jarlandre.rain.entity.impl.projectiles.Projectile;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.level.Level;

public class BulletProjectile extends Projectile {
	public static final int FIRE_RATE = 15;
	
	public BulletProjectile(int x, int y, double angle) {
		super(x, y, angle);
		this.range = 150;
		this.speed = 3;
		this.damage = 20;
		this.nx = speed * Math.cos(angle);
		this.ny = speed * Math.sin(angle);
		this.sprite = Sprite.bullet;
	}
	
	public void update() {
		if (collision(x, y, nx, ny, 9, level)) {
			remove();
			return;
		}
		move();
	}
	
	public boolean collision(double x, double y, double xa, double ya, int size, Level level) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 - size/2) / sprite.getSize();
			double yt = ((y + ya) + c / 2 + size) / sprite.getSize();
			if (level.getTile((int)xt, (int)yt).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}

	public void move() {
		x += nx;
		y += ny;
		if (calculateDistance() > range) {
			remove();
		}
	}

	private double calculateDistance() {
		return Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
	}
}
