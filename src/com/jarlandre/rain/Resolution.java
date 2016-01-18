package com.jarlandre.rain;

import java.awt.Dimension;

public class Resolution {
	public static int PIXEL_WIDTH = 300;
	public static int PIXEL_HEIGHT = 168;
	public static int SCALE = 3;

	public static Dimension getDimension() {
		return new Dimension(PIXEL_WIDTH * SCALE, PIXEL_HEIGHT * SCALE);
	}
}
