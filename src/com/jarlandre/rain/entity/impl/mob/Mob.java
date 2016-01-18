package com.jarlandre.rain.entity.impl.mob;

import com.jarlandre.rain.Direction;
import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.entity.impl.projectiles.Projectile;
import com.jarlandre.rain.entity.impl.projectiles.impl.BulletProjectile;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;

public abstract class Mob extends Entity{
	protected Sprite sprite;
	protected Direction direction;
	protected boolean moving = false;

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(0, ya);
			move(xa, 0);
			return;
		}
		if (xa > 0) direction = Direction.RIGHT;
		if (xa < 0) direction = Direction.LEFT;
		if (ya > 0) direction = Direction.DOWN;
		if (ya < 0) direction = Direction.UP;
		
		if (!collision(xa, ya)) {
			this.x += xa;
			this.y += ya;
		}
	}
	
	public void update() {
		
	}
	
	public void shoot(int x, int y, double dir) {
		//dir *= 180 / Math.PI;
		Projectile bullet = new BulletProjectile(x, y, dir);
		level.add(bullet);
	}
	
	public boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}
	
	public void render(Screen screen) {
		
	}

}
