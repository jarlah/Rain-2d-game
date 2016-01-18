package com.jarlandre.rain.graphics;

public class Sprite {
	public final static Sprite grass = new Sprite(16, 0, 4, SpriteSheet.TILES);
	public final static Sprite wall1 = new Sprite(16, 2, 3, SpriteSheet.TILES);
	public final static Sprite wall2 = new Sprite(16, 2, 4, SpriteSheet.TILES);
	public final static Sprite floor = new Sprite(16, 4, 4, SpriteSheet.TILES);
	
	public final static Sprite bullet = new Sprite(16, 0, 7, SpriteSheet.TILES);
	
	public final static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
	public final static Sprite player_forward_0 = new Sprite(32, 0, 5, SpriteSheet.TILES);
	public final static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.TILES);
	public final static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.TILES);
	
	public final static Sprite player_backward_0 = new Sprite(32, 2, 5, SpriteSheet.TILES);
	public final static Sprite player_backward_1 = new Sprite(32, 2, 6, SpriteSheet.TILES);
	public final static Sprite player_backward_2 = new Sprite(32, 2, 7, SpriteSheet.TILES);
	
	public final static Sprite player_side_0 = new Sprite(32, 1, 5, SpriteSheet.TILES);
	public final static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.TILES);
	public final static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.TILES);
	

	private final int size;
	private int x, y;
	private final int[] pixels;
	private SpriteSheet sheet;

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		this.size = size;
		this.pixels = new int[size * size];
		load();
	}

	public Sprite(int size, int colour) {
		this.size = size;
		this.pixels = new int[size * size];
		for (int i = 0; i < size * size; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
			}
		}
	}

	public int getSize() {
		return size;
	}

	public int[] getPixels() {
		return pixels;
	}
}
