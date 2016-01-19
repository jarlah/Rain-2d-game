package com.jarlandre.rain.entity.impl.mob.impl;

import com.jarlandre.rain.Animation;
import com.jarlandre.rain.Direction;
import com.jarlandre.rain.entity.impl.mob.Mob;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;

public class Ogre extends Mob {
	private Animation side, down, up, still;
	private Animation current;
	
	private double xa = 0, ya = 0;		
	private int time;
	
	public Ogre(double x, double y) {
		this.x = x;
		this.y = y;
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

	@Override
	public void update() {
		current.update();
		
		if (time > 7400) time = 0;
		time++;
		
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		if (xa < 0) {
			if (current != side) {
				side.reset(true);
				current = side;
			}
		} else if (xa > 0) {
			if (current != side) {
				side.reset(true);
				current = side;
			}
		}
		
		if (ya < 0) {
			if (current != up) {
				up.reset(true);
				current = up;
			}
		} else if (ya > 0) {
			if (current != down) {
				down.reset(true);
				current = down;
			}
		}

		move(xa, ya);
		
		current.reset(walking);
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
