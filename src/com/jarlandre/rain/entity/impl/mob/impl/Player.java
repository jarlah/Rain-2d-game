package com.jarlandre.rain.entity.impl.mob.impl;

import com.jarlandre.rain.Coordinate;
import com.jarlandre.rain.Direction;
import com.jarlandre.rain.Resolution;
import com.jarlandre.rain.entity.impl.mob.Mob;
import com.jarlandre.rain.entity.impl.projectiles.impl.BulletProjectile;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.input.KeyboardHandler;
import com.jarlandre.rain.input.MouseHandler;

public class Player extends Mob {
	private int anim = 0;
	private int fireRate = 0;

	public Player(Coordinate coord) {
		this.x = coord.getX();
		this.y = coord.getY();
		this.fireRate = BulletProjectile.FIRE_RATE;
	}

	public void update() {
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if (anim < 7500) anim++;
		else anim = 0;
		if (KeyboardHandler.isUp())
			ya--;
		if (KeyboardHandler.isDown())
			ya++;
		if (KeyboardHandler.isLeft())
			xa--;
		if (KeyboardHandler.isRight())
			xa++;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
		}
		
		doShooting();
	}

	private void doShooting() {
		if (MouseHandler.getButton() == 1 && fireRate <= 0) {
			double dx = MouseHandler.geX() - Resolution.getDimension().getWidth() / 2;
			double dy = MouseHandler.getY() - Resolution.getDimension().getHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = BulletProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		boolean xflip = false, yflip = false;
		sprite = Sprite.player_backward_0;
		if (Direction.UP == direction) {
			sprite = Sprite.player_forward_0;
			if (moving) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (Direction.DOWN == direction) {
			sprite = Sprite.player_backward_0;
			if (moving) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_backward_1;
				} else {
					sprite = Sprite.player_backward_2;
				}
			}
		}
		if (Direction.RIGHT == direction || Direction.LEFT == direction) {
			sprite = Sprite.player_side_0;
			if (moving) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
		}
		if (Direction.LEFT == direction) {
			xflip = true;
		}
		int xx = x - sprite.getSize() / 2;
		int yy = y - sprite.getSize() / 2;
		screen.renderPlayer(xx, yy, sprite, xflip, yflip);
	}

}
