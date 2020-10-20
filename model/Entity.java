package model;

import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.Timer;

import view.GamePanel;

public abstract class Entity implements IEntityInfo, IEntityRender {

	private BufferedImage image;

	protected GamePanel gamePanel;

	protected int currentWidth;
	protected int currentHeight;
	protected int speedMult;
	protected int x;
	protected int y;
	protected int hbX;
	protected int hbY;
	protected int hbWidth;
	protected int hbHeight;
	protected Timer powerUpTimer;

	public Color color;
	public Rectangle entityShape;
	boolean collision = false;

	public Entity() {
		x = 0;
		y = 0;
		speedMult = 1;
		currentWidth = width;
		currentHeight = height;
	}

	public Entity(int speedMult, int x, int y, Color color, double sizeMult) {
		this.speedMult = speedMult;
		this.x = x;
		this.y = y;
		this.color = color;
		currentWidth = (int) (width * sizeMult);
		currentHeight = (int) (height * sizeMult);
		hbWidth = currentWidth - 10;
		hbHeight = height - 10;
		entityShape = new Rectangle(x, y, currentWidth, currentHeight);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedMult() {
		return speedMult;
	}

	public Timer getPowerUpTimer() {
		return powerUpTimer;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setX(int x) {
		this.x = x;
		this.hbX = x + 20;
		updateShape();
	}

	public void setY(int y) {
		this.y = y;
		this.hbY = y + 20;
		updateShape();
	}

	public void setHbX(int hbX) {
		this.hbX = hbX;
		this.x = hbX - 20;
		updateShape();
	}

	public void setHbY(int hbY) {
		this.hbY = hbY;
		this.y = hbY - 20;
		updateShape();
	}

	public void updatePlayerShape(Rectangle playerShape) { }

	// public void updateShape() {
	// 	entityShape = new Rectangle(x, y, currentWidth, currentHeight);
	// }

	abstract public void updateShape();

	public int getSpeed() {
		return baseSpeed + speedMult;
	}

	public Rectangle getCurrentEntityShape() {
		return entityShape;
	}

	public void setSpeedMult(int speedMult) {
		this.speedMult = speedMult;
	}

	public abstract void update();

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void resetTimer() { }
	public void spawnNewLocation() { }

	
}
