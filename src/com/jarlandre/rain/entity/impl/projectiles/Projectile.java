package com.jarlandre.rain.entity.impl.projectiles;

import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.level.Level;

public abstract class Projectile extends Entity {
	// Original position (where it started)
	protected final double xOrigin, yOrigin;
	// THe angle the projectile should aim at
	protected double angle;
	// The number we add to x and y when moving
	protected double nx, ny;
	// Projectile info
	protected double speed, range, damage;

	public Projectile(double x, double y, double angle) {
		this.xOrigin = x;
		this.yOrigin = y;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.damage = 1;
	}
	
	public boolean collision(double x, double y, double xa, double ya, int size, Level level) {
		return false;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x - sprite.width(), (int) y - sprite.height() / 2, sprite, false, false, false);
	}

	protected void move() {

	}

	public double getDamage() {
		return damage;
	}
}
