package model;

import java.awt.Color;

import controller.Key;
import view.GameCanvas;
import view.GamePanel;

public class GameContainer implements Runnable {

	private GamePanel panel;

	private Thread thread;
	private boolean running = false;
	private final double SECOND = 1;
	private final double UPDATE_CAP = 1.0 / 60.0; // capping updates to 60 times a second
	public static int score = 0;

	// public static void main(String[] args) {
	// 	GameContainer gc = new GameContainer();
	// 	gc.start();
	// }

	public GameContainer(GamePanel panel) {
		this.panel = panel;
	}

	public void start() {
		thread = new Thread(this);
		score = 0;
		thread.start();
	}

	public void stop() {
		Key.resetKeys();
		thread.stop(); // unsafe find fix for thread stop
	}

	public void run() {
		running = true;

		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double secondTime = 0;
		double secondCount = 0;

		while (running) {
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			

			unprocessedTime += passedTime;
			secondTime += passedTime;

			//System.out.println(unprocessedTime);

			if (secondTime >= SECOND) {
				secondCount++;
				score++;
				secondTime = 0;
			}
			if (secondCount == 5) {
				// adding enemies
				GamePanel.speedSetting += 0.5;
				panel.addEnemy(GamePanel.speedSetting, 885, 430, Color.RED, GamePanel.sizeSetting);
				for (var e: panel.getEnemies()) {
					panel.changeEntitySpeed(e, GamePanel.speedSetting);
				}

				panel.getPowerUp().spawnNewLocation();

				secondCount = 0;
			}

			while (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;
				panel.update();
			}

			if (render) {
				panel.render();
			} else {
				try {
					Thread.sleep(1);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		dispose();
	}

	private void dispose() {

	}
}
