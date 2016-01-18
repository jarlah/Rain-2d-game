package com.jarlandre.rain.graphics;

public class Sprite {
	public final static Sprite grass = new Sprite(16, 16, 0, 4, SpriteSheet.TILES);
	public final static Sprite wall1 = new Sprite(16, 16, 2, 3, SpriteSheet.TILES);
	public final static Sprite wall2 = new Sprite(16, 16, 2, 4, SpriteSheet.TILES);
	public final static Sprite floor = new Sprite(16, 16, 4, 4, SpriteSheet.TILES);
	public final static Sprite bullet = new Sprite(16, 16, 0, 7, SpriteSheet.TILES);
	public final static Sprite crossHair = new Sprite(16, 16, 0, 8, SpriteSheet.TILES);
	
	public final static Sprite voidSprite = new Sprite(16, 16, 0x1B87E0);
	public final static Sprite particle_normal = new Sprite(3, 3, 0xAAAAAA);
	
	public final static Sprite player_forward_0 = new Sprite(32, 32, 0, 5, SpriteSheet.TILES);
	public final static Sprite player_forward_1 = new Sprite(32, 32, 0, 6, SpriteSheet.TILES);
	public final static Sprite player_forward_2 = new Sprite(32, 32, 0, 7, SpriteSheet.TILES);
	
	public final static Sprite player_backward_0 = new Sprite(32, 32, 2, 5, SpriteSheet.TILES);
	public final static Sprite player_backward_1 = new Sprite(32, 32, 2, 6, SpriteSheet.TILES);
	public final static Sprite player_backward_2 = new Sprite(32, 32, 2, 7, SpriteSheet.TILES);
	
	public final static Sprite player_side_0 = new Sprite(32, 32, 1, 5, SpriteSheet.TILES);
	public final static Sprite player_side_1 = new Sprite(32, 32, 1, 6, SpriteSheet.TILES);
	public final static Sprite player_side_2 = new Sprite(32, 32, 1, 7, SpriteSheet.TILES);
	

	private final int width, height;
	private int x, y;
	private final int[] pixels;
	private SpriteSheet sheet;

	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.x = x * width;
		this.y = y * height;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		this.pixels = new int[width * width];
		load();
	}
	
	public Sprite(int width, int height, int colour) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
			}
		}
	}

	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}

	public int[] pixels() {
		return pixels;
	}
}
