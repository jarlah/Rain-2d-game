package com.jarlandre.rain;

public class Coordinate {
	private final double x, y;
	
	public Coordinate(Number x, Number y) {
		this.x = x.longValue();
		this.y = y.longValue();
	}
	
	public double distance(Coordinate other) {
		return Math.sqrt(Math.abs((this.x() - other.x()) * (this.x() - other.x()) + (this.y() - other.y()) * (this.y() - other.y())));
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
	
}
