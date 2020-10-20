package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

import model.images.ImageStore;

public class GameCanvas extends JPanel {

	public static final int WIDTH = 1275;
	public static final int HEIGHT = 650;
	public static final int borderWidth = 15;
	public static Rectangle[] borders = new Rectangle[4]; // 0 = left, 1 = top, 2 = right, 3 = bottom

	private GamePanel gamePanel;

	public GameCanvas(GamePanel gamePanel) {
		gamePanel.getWindow().setLocation(-5, 0);
		this.gamePanel = gamePanel;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initBorders();
	}

	public static Rectangle[] getBorders() {
		return borders;
	}

	public void initBorders() {
		borders[0] = new Rectangle(0, 0, borderWidth, HEIGHT);
		borders[1] = new Rectangle(WIDTH - borderWidth, 0, borderWidth, HEIGHT);
		borders[2] = new Rectangle(borderWidth, 0, WIDTH, borderWidth);
		borders[3] = new Rectangle(borderWidth, HEIGHT - borderWidth, WIDTH, borderWidth);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// rendering background
		g2.drawImage(ImageStore.background, null, 0, 0);

		// rendering walls
		g2.setColor(Color.black);
		g2.fill(borders[0]); // left
		g2.fill(borders[1]); // top
		g2.fill(borders[2]); // right
		g2.fill(borders[3]); // bottom

		// rendering player
		gamePanel.getPlayer().render(g2);
		gamePanel.getPowerUp().render(g2);
		// rendering enemies
		for (var e: gamePanel.getEnemies()) {
			e.render(g2);
		}
	}
}
