package com.jarlandre.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.jarlandre.rain.entity.impl.mob.impl.Player;
import com.jarlandre.rain.graphics.Screen;
import com.jarlandre.rain.graphics.Sprite;
import com.jarlandre.rain.input.KeyboardHandler;
import com.jarlandre.rain.input.MouseHandler;
import com.jarlandre.rain.level.Level;
import com.jarlandre.rain.tile.TileCoordinate;

public class Game extends Loop {
	private final static String TITLE = "Rain";
	
	private final JFrame window;
	private final KeyboardHandler keyListener;
	private final MouseHandler mouseListener;
	private final Canvas canvas;
	private final BufferedImage image;
	private final int[] pixels;
	private final Screen screen;
	private final Level level;
	private final Player player;
	private final Random random;
	
	public Game(String title) {
		super(TITLE);
		this.canvas = new Canvas();
		this.canvas.setPreferredSize(Resolution.getDimension());
		this.canvas.setMinimumSize(Resolution.getDimension());
		this.canvas.setMaximumSize(Resolution.getDimension());
		this.image = new BufferedImage(Resolution.PIXEL_WIDTH, Resolution.PIXEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt)this.image.getRaster().getDataBuffer()).getData();
		this.screen = new Screen(Resolution.PIXEL_WIDTH, Resolution.PIXEL_HEIGHT);
		this.window = new JFrame(title);
		this.window.setResizable(false);
		this.window.add(canvas);
		this.window.pack();
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLocationRelativeTo(null);
		this.window.setVisible(true);
		this.keyListener = new KeyboardHandler();
		this.mouseListener = new MouseHandler();
		this.canvas.addKeyListener(keyListener);
		this.canvas.addMouseListener(mouseListener);
		this.canvas.addMouseMotionListener(mouseListener);
		this.level = Level.level1;
		this.player = new Player(new TileCoordinate(10, 52));
		this.player.setLevel(level);
		this.random = new Random();
		this.canvas.requestFocus();
	}
	
	public static void main(String[] args) {
		Game game = new Game("Rain");
		game.start();
	}

	@Override
	public void update() {
		player.update();
		level.update();
	}

	@Override
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = (int) player.x() - screen.getWidth() / 2;
		int yScroll = (int) player.y() - screen.getHeight() / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		Sprite sprite = new Sprite(2, 2, 0xffffff);
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			screen.renderSprite(10 * 16 + x, 52 * 16 + y, sprite, false, false, false);
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		window.setTitle(TITLE + " | " + getFps() + " fps, " + getUps() + " update" + (getUps() > 1 ? " catchup" : "") + " per render");
		//g.fillRect(MouseHandler.getMouseX() - 32, MouseHandler.getMouseY() - 32, 64, 64);
		//g.drawString("Mouse: " + MouseHandler.getMouseB(), 80, 80);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		bs.show();
	}

}
