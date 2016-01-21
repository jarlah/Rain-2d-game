package com.jarlandre.rain.entity.impl.mob.impl;

import java.util.List;

import com.jarlandre.rain.Animation;
import com.jarlandre.rain.Direction;
import com.jarlandre.rain.Node;
import com.jarlandre.rain.Vector2i;
import com.jarlandre.rain.entity.impl.mob.Mob;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.tile.Tile;

public class Star extends Mob {
	private Animation side, down, up, still;
	private Animation current;
	private double xa = 0, ya = 0;	
	
	public Star(double x, double y) {
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
		timer++;
		move();
	}
	
	private List<Node> path = null;
	
	private int timer;
	
	public void move() {
		xa = 0;
		ya = 0;
	
		if (timer % 60 == 0) {
			timer = 0;
			Player player = level.getClientPlayer();
			Vector2i start = new Vector2i((int)(this.x / Tile.TILE_SIZE), (int) (this.y / Tile.TILE_SIZE));
			Vector2i goal = new Vector2i((int) (player.x() / Tile.TILE_SIZE), (int) (player.y() / Tile.TILE_SIZE));
			path = level.findPath(start, goal);
		}
		if (path != null) {
			if (path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).getTile();
				if (x < vec.getX() << 4) xa--;
				if (x > vec.getX() << 4) xa++;
				if (y < vec.getY() << 4) ya--;
				if (y > vec.getY() << 4) ya++;
			}
		}
		if (xa != 0 && ya != 0) {
			move(xa, ya);	
			walking = true;
		} else {
			walking = false;
		}
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
