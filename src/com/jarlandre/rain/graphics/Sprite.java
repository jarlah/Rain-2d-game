package com.jarlandre.rain.graphics;

public class Sprite {
	public final static Sprite grass = new Sprite(16, 16, 0, 4, SpriteSheet.tiles);
	public final static Sprite wall1 = new Sprite(16, 16, 2, 3, SpriteSheet.tiles);
	public final static Sprite wall2 = new Sprite(16, 16, 2, 4, SpriteSheet.tiles);
	public final static Sprite floor = new Sprite(16, 16, 4, 4, SpriteSheet.tiles);
	public final static Sprite bullet = new Sprite(16, 16, 0, 7, SpriteSheet.tiles);
	public final static Sprite crossHair = new Sprite(16, 16, 0, 8, SpriteSheet.tiles);
	
	public final static Sprite voidSprite = new Sprite(16, 16, 0x1B87E0);
	public final static Sprite particle_normal = new Sprite(3, 3, 0xAAAAAA);
	
	public final static Sprite ogre_up_0 = new Sprite(32, 32, 0, 0, SpriteSheet.ogre_up);
	public final static Sprite ogre_up_1 = new Sprite(32, 32, 1, 0, SpriteSheet.ogre_up);
	public final static Sprite ogre_up_2 = new Sprite(32, 32, 2, 0, SpriteSheet.ogre_up);
	
	public final static Sprite ogre_down_0 = new Sprite(32, 32, 0, 0, SpriteSheet.ogre_down);
	public final static Sprite ogre_down_1 = new Sprite(32, 32, 1, 0, SpriteSheet.ogre_down);
	public final static Sprite ogre_down_2 = new Sprite(32, 32, 2, 0, SpriteSheet.ogre_down);
	
	public final static Sprite ogre_side_0 = new Sprite(32, 32, 0, 0, SpriteSheet.ogre_right);
	public final static Sprite ogre_side_1 = new Sprite(32, 32, 1, 0, SpriteSheet.ogre_right);
	public final static Sprite ogre_side_2 = new Sprite(32, 32, 2, 0, SpriteSheet.ogre_right);
	
	public final static Sprite player_up_0 = new Sprite(32, 32, 0, 0, SpriteSheet.player_up);
	public final static Sprite player_up_1 = new Sprite(32, 32, 1, 0, SpriteSheet.player_up);
	public final static Sprite player_up_2 = new Sprite(32, 32, 2, 0, SpriteSheet.player_up);
	public final static Sprite player_up_3 = new Sprite(32, 32, 3, 0, SpriteSheet.player_up);
	
	public final static Sprite player_down_0 = new Sprite(32, 32, 0, 0, SpriteSheet.player_down);
	public final static Sprite player_down_1 = new Sprite(32, 32, 1, 0, SpriteSheet.player_down);
	public final static Sprite player_down_2 = new Sprite(32, 32, 2, 0, SpriteSheet.player_down);
	public final static Sprite player_down_3 = new Sprite(32, 32, 3, 0, SpriteSheet.player_down);
	
	public final static Sprite player_side_0 = new Sprite(32, 32, 0, 0, SpriteSheet.player_right);
	public final static Sprite player_side_1 = new Sprite(32, 32, 1, 0, SpriteSheet.player_right);
	public final static Sprite player_side_2 = new Sprite(32, 32, 2, 0, SpriteSheet.player_right);
	public final static Sprite player_side_3 = new Sprite(32, 32, 3, 0, SpriteSheet.player_right);
	

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
				pixels[x + y * width] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getWidth()];
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
