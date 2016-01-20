package com.jarlandre.rain.entity.impl.mob.impl;

import com.jarlandre.rain.Animation;
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
	private int fireRate = 0;
	private Animation side, down, up, still;
	private Animation current;
	
	public Player(Coordinate coord) {
		this.x = coord.x();
		this.y = coord.y();
		this.speed = 0.6;
		this.health = 10;
		this.fireRate = BulletProjectile.FIRE_RATE;
		
		this.still = new Animation();
		this.still.setDelay(-1);
		this.still.setFrames(new Sprite[] {
		  Sprite.player_down_1
		}, 32, 32);
		
		this.down = new Animation();
		this.down.setDelay(100);
		this.down.setFrames(new Sprite[] {
		  Sprite.player_down_3, Sprite.player_down_0, Sprite.player_down_1, Sprite.player_down_2
		}, 32, 32);
		
		this.up = new Animation();
		this.up.setDelay(100);
		this.up.setFrames(new Sprite[] {
		  Sprite.player_up_3, Sprite.player_up_0, Sprite.player_up_1, Sprite.player_up_2
		}, 32, 32);
		
		this.side = new Animation();
		this.side.setDelay(100);
		this.side.setFrames(new Sprite[] {
		  Sprite.player_side_3, Sprite.player_side_0, Sprite.player_side_1, Sprite.player_side_2
		}, 32, 32);
		
		this.current = still;
	}

	public void update() {
		current.update();
		
		if (fireRate > 0) fireRate--;
		
		double xa = 0, ya = 0;		
		if (KeyboardHandler.isLeft()) {
			if (current != side) {
				current = side;
			}
			xa -= speed;
		} else if (KeyboardHandler.isRight()) {
			if (current != side) {
				current = side;
			}
			xa += speed;
		}
		
		if (KeyboardHandler.isUp()) {
			if (current != up) {
				current = up;
			}
			ya -= speed;
		} else if (KeyboardHandler.isDown()) {
			if (current != down) {
				current = down;
			}
			ya += speed;
		}

		move(xa, ya);
		
		current.reset(KeyboardHandler.isMoving());

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
		boolean xflip = false;
		if (Direction.LEFT == currentDirection) {
			xflip = true;
		}
		int xx = (int)x - current.getImage().width() / 2;
		int yy = (int)y - current.getImage().height() / 2;
		screen.renderSprite(xx, yy, current.getImage(), false, xflip, false);
	}

}
