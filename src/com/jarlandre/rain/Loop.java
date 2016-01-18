package com.jarlandre.rain;

public abstract class Loop implements Runnable {
	private static final double GAME_HERTZ = 60.0;
	private static final double TIME_BETWEEN_UPDATES = 1000000000.0 / GAME_HERTZ;
	private static final double TARGET_FPS = 120.0;
	private static final double TARGET_TIME_BETWEEN_RENDERS = 1000000000.0 / TARGET_FPS;
	private static final double MAX_UPDATES_BEFORE_RENDER = 5;
	
	private final String name;
	private Thread thread;
	private boolean running;
	private int fps, ups;
	
	public Loop(String name) {
		this.name = name;
		this.running = false;
	}
	
	public synchronized void start() {
		this.running = true;
		this.thread = new Thread(this, name);
		this.thread.start();
	}
	
	public synchronized void stop() {
		this.running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		int frameCount = 0;
		
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			while (now - lastUpdateTime > TIME_BETWEEN_UPDATES
					&& updateCount < MAX_UPDATES_BEFORE_RENDER) {
				update();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}

			if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}

			render();
			
			frameCount ++;

			lastRenderTime = now;

			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) {
				fps = frameCount;
				frameCount = 0;
				ups = updateCount;
				lastSecondTime = thisSecond;
			}

			while ((now - lastRenderTime) < TARGET_TIME_BETWEEN_RENDERS
					&& (now - lastUpdateTime) < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				try {Thread.sleep(1);} catch (Exception e) {}
				now = System.nanoTime();
			}
		}
		stop();
	}
	
	public abstract void update();
	public abstract void render();

	public int getFps() {
		return fps;
	}
	
	public int getUps() {
		return ups;
	}

}
