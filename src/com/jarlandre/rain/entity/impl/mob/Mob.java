package com.jarlandre.rain.entity.impl.mob;

import com.jarlandre.rain.Direction;
import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.entity.impl.projectiles.impl.BulletProjectile;
import com.jarlandre.rain.graphics.Screen;

public abstract class Mob extends Entity {
	protected Direction currentDirection;
	
	protected double speed, damage, health, range;
	
	protected boolean walking = false;

	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(0, ya);
			move(xa, 0);
			return;
		}
		
		currentDirection = Direction.NONE;
		if (xa > 0) currentDirection = Direction.RIGHT;
		if (xa < 0) currentDirection = Direction.LEFT;
		if (ya > 0) currentDirection = Direction.DOWN;
		if (ya < 0) currentDirection = Direction.UP;
		
		while (xa != 0) {
			int direction = getDirection(xa);
			if (Math.abs(xa) > 1) {
				if (!level.pixelCollision(x + direction, y + ya)) {
					this.x += direction;
				}
				xa -= direction;
			} else {
				if (!level.pixelCollision(x + direction, y + ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}
		
		while (ya != 0) {
			int direction = getDirection(ya);
			if (Math.abs(ya) > 1) {
				if (!level.pixelCollision(x + xa, y + direction)) {
					this.y += direction;
				}
				ya -= direction;
			} else {
				if (!level.pixelCollision(x + xa, y + direction)) {
					this.y += ya;
				}
				ya = 0;
			}
		}
	}

	private int getDirection(double n) {
		return n < 0 ? -1 : 1;
	}
	
	public abstract void update();
	public abstract void render(Screen screen);
	
	public void shoot(double x, double y, double dir) {
		level.add(new BulletProjectile(x, y, dir));
	}
}
