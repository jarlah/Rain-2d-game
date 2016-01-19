package com.jarlandre.rain;

import com.jarlandre.rain.graphics.Sprite;

public class Animation {

	private Sprite[] frames;
	private int currentFrame;

	private long startTime;
	private long delay;

	private boolean playedOnce, active;
	
	private int width, height;

	public void setFrames(Sprite[] frames, int width, int height) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
		active = true;
		this.width = width;
		this.height = height;
	}

	public void setDelay(long d) {
		delay = d;
	}

	public void setFrame(int i) {
		currentFrame = i;
	}
	
	public void reset(boolean b) {
		if (!active) {
			currentFrame = 0;
		}
		active = b;
	}

	public void update() {

		if (delay == -1 || !active)
			return;

		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if (elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if (currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}

	}

	public int getFrame() {
		return currentFrame;
	}

	public Sprite getImage() {
		return frames[currentFrame];
	}

	public boolean hasPlayedOnce() {
		return playedOnce;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
