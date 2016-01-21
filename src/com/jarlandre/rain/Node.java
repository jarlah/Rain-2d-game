package com.jarlandre.rain;

public class Node {
	private Vector2i tile;
	private Node parent;
	private double fCost, gCost, hCost;
	
	public Node(Vector2i tile, Node parent, double gCost, double hCost){
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}

	public Vector2i getTile() {
		return tile;
	}

	public void setTile(Vector2i tile) {
		this.tile = tile;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public double getfCost() {
		return fCost;
	}

	public void setfCost(double fCost) {
		this.fCost = fCost;
	}

	public double getgCost() {
		return gCost;
	}

	public void setgCost(double gCost) {
		this.gCost = gCost;
	}

	public double gethCost() {
		return hCost;
	}

	public void sethCost(double hCost) {
		this.hCost = hCost;
	}
}
