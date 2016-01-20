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
		this.currentFrame = 0;
		this.startTime = System.nanoTime();
		this.playedOnce = false;
		this.active = true;
		this.width = width;
		this.height = height;
	}

	public void setDelay(long d) {
		this.delay = d;
	}

	public void setFrame(int i) {
		this.currentFrame = i;
	}
	
	public void reset() {
		reset(!this.active);
	}
	
	public void reset(boolean active) {
		if (!this.active) {
			this.currentFrame = 0;
		}
		this.active = active;
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
