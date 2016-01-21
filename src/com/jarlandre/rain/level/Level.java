package com.jarlandre.rain.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.jarlandre.rain.Node;
import com.jarlandre.rain.Vector2i;
import com.jarlandre.rain.entity.Entity;
import com.jarlandre.rain.entity.impl.mob.impl.ClientPlayer;
import com.jarlandre.rain.entity.impl.mob.impl.Ogre;
import com.jarlandre.rain.entity.impl.mob.impl.Player;
import com.jarlandre.rain.entity.impl.projectiles.Projectile;
import com.jarlandre.rain.entity.impl.spawner.impl.ParticleSpawner;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.level.impl.SpawnLevel;
import com.jarlandre.rain.tile.Tile;

public abstract class Level {
	public static final Level level1 = new SpawnLevel("textures/spawn_level.png");

	protected int width, height;
	
	protected int[] tiles;
	
	private List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());
	
	private Player clientPlayer; // cache

	public Level(String path) {
		loadLevel(path);
	}

	protected abstract void loadLevel(String path);

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);	
			if (e.isRemoved()) {
				entities.remove(e);
			} else {
				e.update();
			}
		}
	}
	
	/**
	 * <code>
	 * Entity enemyMob = ...;<br />
	 * getEntities(enemyMob, 20, entity -> entity instanceof Player);
	 * </code>
	 * 
	 * @param e
	 * @param radius
	 * @param typePredicate
	 * @return
	 */
	public List<Entity> getEntities(Entity e, int radius, Predicate<Entity> typePredicate) {
		List<Entity> result = new ArrayList<Entity>();
		double ex = e.x();
		double ey = e.y();
		for (Entity entity: entities) {
			double x = entity.x();
			double y = entity.y();
			double dx = Math.abs(x - ex);
			double dy = Math.abs(y - ey);
			// diagonal distance = square root of x^2 + y^2
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius && typePredicate.test(entity)) {
				result.add(entity);
			}
		}
		return result;
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {	
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * size + xOffset) / Tile.TILE_SIZE;
			double yt = (y - c / 2 * size + yOffset) / Tile.TILE_SIZE;
			if (getTile((int)xt, (int)yt).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}
	
	public boolean pixelCollision(double x, double y) {
		boolean solid = false;
		
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * Tile.TILE_SIZE) / Tile.TILE_SIZE;
			double yt = (y - c / 2 * Tile.TILE_SIZE) / Tile.TILE_SIZE;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (getTile(ix, iy).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffsets(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.getWidth() + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == Tile.grass_colour)
			return Tile.grass;
		if (tiles[x + y * width] == Tile.wall1_colour)
			return Tile.wall1;
		if (tiles[x + y * width] == Tile.wall2_colour)
			return Tile.wall2;
		if (tiles[x + y * width] == Tile.floor_colour)
			return Tile.floor;
		return Tile.voidTile;
	}

	public void add(Entity e) {
		e.setLevel(this);
		if (e instanceof ClientPlayer) {
			clientPlayer = (Player) e;
		}
		this.entities.add(e);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public Player getClientPlayer() {
		return clientPlayer;
	}
	
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, start.distanceTo(goal));
		openList.add(current);
		while(!openList.isEmpty()) {
			Collections.sort(openList, new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					if (n1.getfCost() < n2.getfCost()) return +1;
					if (n1.getfCost() > n2.getfCost()) return -1;
					return 0;
				}
			});
			current = openList.get(0);
			if (current.getTile().equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.getParent() != null) {
					path.add(current);
					current = current.getParent();
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.getTile().getX();
				int y = current.getTile().getY();
				int dx = (i % 3) - 1;
				int dy = (i / 3) - 1;
				Tile atTile = getTile(x + dx, y + dy);
				if (atTile == null) continue;
				if (atTile.solid()) continue;
				Vector2i atVector = new Vector2i(x + dx, y + dy);
				double gCost = current.getgCost() + current.getTile().distanceTo(atVector);
				double hCost = atVector.distanceTo(goal);
				Node node = new Node(atVector, current, gCost, hCost);
				if (atVector.inNodeList(closedList) && gCost >= node.getgCost()) continue;
				if (!atVector.inNodeList(openList) || gCost < node.getgCost()) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}

	public boolean projectileCollision(int x, int y, Projectile projectile) {
		List<Entity> entities = getEntities(projectile, 10, e -> e instanceof Ogre);
		if (entities.isEmpty()) return false;
		Ogre ogre = (Ogre) entities.get(0);
		ogre.hit((int)projectile.getDamage());
		new ParticleSpawner(x, y, 44, 50, this).spawn();
		projectile.remove();
		return true;
	}
}
