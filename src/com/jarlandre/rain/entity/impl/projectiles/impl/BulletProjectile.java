package com.jarlandre.rain.entity.impl.projectiles.impl;

import com.jarlandre.rain.Coordinate;
import com.jarlandre.rain.entity.impl.projectiles.Projectile;
import com.jarlandre.rain.entity.impl.spawner.impl.ParticleSpawner;
import com.jarlandre.rain.graphics.Sprite;

public class BulletProjectile extends Projectile {
	public static final int FIRE_RATE = 15;

	public BulletProjectile(double x, double y, double angle) {
		super(x, y, angle);
		this.range = 150;
		this.speed = 3;
		this.damage = 1;
		this.nx = speed * Math.cos(angle);
		this.ny = speed * Math.sin(angle);
		this.sprite = Sprite.bullet;
	}

	public void update() {
		if (!level.projectileCollision((int) (x + nx), (int) (y + ny), this)) {
			if (level.tileCollision((int) (x + nx), (int) (y + ny), 9, 3, 3)) {
				new ParticleSpawner(x, y, 44, 50, level).spawn();
				remove();
				return;
			}
			move();
		}
	}

	public void move() {
		x += nx;
		y += ny;
		Coordinate origin = new Coordinate(xOrigin, yOrigin);
		Coordinate current = new Coordinate(x, y);
		if (origin.distance(current) > range) {
			remove();
		}
	}
}
