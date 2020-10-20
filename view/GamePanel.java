package view;

import model.Player;
import model.PowerUp;
import model.Enemy;
import model.Entity;
import model.GameContainer;

import java.awt.Container;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GamePanelListener;

public class GamePanel {
	private JFrame window;
	private JPanel canvas;
	
	Entity player = new Player(-1, 200, 300, Color.blue, 1);
	ArrayList<Entity> enemies = new ArrayList<>();
	Entity powerUp = new PowerUp(0, 300, 300, Color.yellow, 0.5);

	// varialbes for game settings
	public static int speedSetting = -5;
	public static double sizeSetting = 1;

	GameContainer gc = new GameContainer(this);

	public GamePanel(JFrame window) {
		this.window = window;
	}

	public void init() {
		Container cp = window.getContentPane();
		addEnemy(speedSetting, 885, 430, Color.red, sizeSetting);
		powerUp.setGamePanel(this);
		
		canvas = new GameCanvas(this);
		cp.add(canvas);

		GamePanelListener listener = new GamePanelListener(this);
		canvas.addKeyListener(listener);
		canvas.requestFocus();
		canvas.setFocusable(true);
	}

	public void addEnemy(int speedMult, int x, int y, Color color, double sizeMult) {
		System.out.println(sizeMult);
		Entity enemy = new Enemy(speedMult, x, y, color, sizeMult);
		enemy.setGamePanel(this);
		enemies.add(enemy);
	}

	public void changeEntitySpeed(Entity entity, int change) {
		entity.setSpeedMult(change);
	}

	public void update() {
		player.update();
		powerUp.updatePlayerShape(player.getCurrentEntityShape());
		powerUp.update();
		for (var e: enemies) {
			e.updatePlayerShape(player.getCurrentEntityShape());
			e.update();
		}
	}

	public Entity getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEnemies() {
		return enemies;
	}

	public Entity getPowerUp() {
		return powerUp;
	}

	public void render() {
		canvas.repaint();
	}

	public GameContainer getGc() {
		return gc;
	}

	public JFrame getWindow() {
		return window;
	}
	
}
