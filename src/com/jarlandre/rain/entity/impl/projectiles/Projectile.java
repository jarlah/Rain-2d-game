package com.jarlandre.rain.entity.impl.projectiles;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.level.Level;

public abstract class Projectile extends Entity {
	// The coordinates the projectile stems from
	protected final int xOrigin, yOrigin;
	// Precise position
	protected double x, y;
	// THe angle the projectile should aim at
	protected double angle;
	// The sprite, obviously
	protected Sprite sprite;
	// The number we add to x and y when moving
	protected double nx, ny;
	// Projectile info
	protected double speed, range, damage;

	public Projectile(int x, int y, double angle) {
		this.xOrigin = x;
		this.yOrigin = y;
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public boolean collision(double x, double y, double xa, double ya, int size, Level level) {
		return false;
	}

	public void render(Screen screen) {
		screen.renderTile((int) x - 12, (int) y - 2, sprite);
	}

	protected void move() {

	}
}
