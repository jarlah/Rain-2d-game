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
		
		if (xa > 0)
			currentDirection = Direction.RIGHT;
		if (xa < 0)
			currentDirection = Direction.LEFT;
		if (ya > 0)
			currentDirection = Direction.DOWN;
		if (ya < 0)
			currentDirection = Direction.UP;
		
		if (Direction.NONE == currentDirection) {
			walking = false;
		} else if (!collision(xa, ya)) {
			this.x += xa;
			this.y += ya;
			walking = true;
		}
	}
	
	public abstract void update();
	public abstract void render(Screen screen);
	
	public void shoot(double x, double y, double dir) {
		level.add(new BulletProjectile(x, y, dir));
	}
	
	public boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			double yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile((int)xt, (int)yt).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}
}
