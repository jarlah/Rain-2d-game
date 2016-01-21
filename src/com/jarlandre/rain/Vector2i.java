package com.jarlandre.rain;

import java.util.List;

/**
 * Non-final integer vector class, fields can be mutated.
 *  
 * @author jarlandre
 *
 */
public class Vector2i {
	private int x, y;

	public Vector2i() {
		this(0, 9);
	}
	
	public Vector2i(Vector2i v) {
		this(v.x, v.y);
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i add(Vector2i v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	
	public Vector2i subtract(Vector2i v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}
	
	public double distanceTo(Vector2i goal) {
		double dx = this.x - goal.x;
		double dy = this.y - goal.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public int getX() {
		return x;
	}

	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Vector2i)) return false;
		Vector2i otherVector = (Vector2i) other;
		if (this.x == otherVector.x && this.y == otherVector.y) return true;
		return false;	
	}
	
	public boolean inNodeList(List<Node> list) {
		for (Node n: list) {
			if (n.getTile().equals(this)) return true;
		}
		return false;
	}
}
