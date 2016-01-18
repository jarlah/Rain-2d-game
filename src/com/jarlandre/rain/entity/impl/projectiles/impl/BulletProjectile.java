package com.jarlandre.rain.entity.impl.projectiles.impl;

import com.jarlandre.rain.Coordinate;
import com.jarlandre.rain.entity.Spawner;
import com.jarlandre.rain.entity.impl.projectiles.Projectile;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.level.Level;

public class BulletProjectile extends Projectile {
	public static final int FIRE_RATE = 15;
	
	public BulletProjectile(double x, double y, double angle) {
		super(x, y, angle);
		this.range = 150;
		this.speed = 3;
		this.damage = 20;
		this.nx = speed * Math.cos(angle);
		this.ny = speed * Math.sin(angle);
		this.sprite = Sprite.bullet;
	}
	
	public void update() {
		if (collision(xCurrent, yCurrent, nx, ny, 9, level)) {
			Spawner spawner = new Spawner(xCurrent, yCurrent, Spawner.Type.PARTICLE, 50);
			spawner.spawn(level);
			remove();
			return;
		}
		move();
	}
	
	public boolean collision(double x, double y, double xa, double ya, int size, Level level) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 - size / 2 - 8) / sprite.width();
			double yt = ((y + ya) + c / 2 - size + 8) / sprite.height();
			if (level.getTile((int)xt, (int)yt).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}

	public void move() {
		xCurrent += nx;
		yCurrent += ny;
		Coordinate origin = new Coordinate(xOrigin, yOrigin);
		Coordinate current = new Coordinate(xCurrent, yCurrent);
		if (origin.distance(current) > range) {
			remove();
		}
	}
}
