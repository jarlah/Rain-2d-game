package com.jarlandre.rain.entity.impl.mob.impl;

import java.util.List;

import com.jarlandre.rain.Animation;
import com.jarlandre.rain.Direction;
import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.entity.impl.mob.Mob;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;

public class Ogre extends Mob {
	private Animation side, down, up, still;
	private Animation current;
	
	public Ogre(double x, double y) {
		this.x = x;
		this.y = y;
		this.speed = 0.8;
		this.health = 5;
		this.still = new Animation();
		this.still.setDelay(-1);
		this.still.setFrames(new Sprite[] {
		  Sprite.ogre_down_1
		}, 32, 32);
		
		this.down = new Animation();
		this.down.setDelay(100);
		this.down.setFrames(new Sprite[] {
		  Sprite.ogre_down_1, Sprite.ogre_down_0, Sprite.ogre_down_2
		}, 32, 32);
		
		this.up = new Animation();
		this.up.setDelay(100);
		this.up.setFrames(new Sprite[] {
		  Sprite.ogre_up_1, Sprite.ogre_up_0, Sprite.ogre_up_2
		}, 32, 32);
		
		this.side = new Animation();
		this.side.setDelay(100);
		this.side.setFrames(new Sprite[] {
		  Sprite.ogre_side_1, Sprite.ogre_side_0, Sprite.ogre_side_2
		}, 32, 32);
		
		this.current = still;
	}
	
	public void hit(int d) {
		this.health -= d;
		if (this.health <= 0) {
			remove();
		}
	}

	@Override
	public void update() {
		current.update();
		
		double xa = 0, ya = 0;		
		
		List<Entity> entities = level.getEntities(this, 80, e -> e instanceof Player);
		if (entities.size() > 0) {
			walking = true;
			Player player = (Player) entities.get(0);
			double pX = player.x();
			double pY = player.y();
			if (x < pX) {
				xa = speed;
				current = side;
			} else if (x > pX) {
				xa = -speed;
				current = side;
			}
			if (y < pY) {
				ya = speed;
				current = down;
			} else if (y > pY) {
				ya = -speed;
				current = up;
			}
		} else {
			walking = false;
			current = still;
		}

		move(xa, ya);
	}

	@Override
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
