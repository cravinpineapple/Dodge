package model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import java.util.Random;

import controller.GamePanelListener;
import model.images.ImageStore;
import view.GameCanvas;

public class PowerUp extends Entity {

	private Rectangle playerShape;

	private Random rand = new Random();

	public PowerUp() {
		super();
	}

	public PowerUp(int speedMult, int x, int y, Color color, double sizeMult) {
		super(speedMult, x, y, color, sizeMult);

		setImage(ImageStore.potion);
		entityShape = new Rectangle(x + 8, y + 8, ImageStore.potXY[0] - 15, ImageStore.potXY[1] - 10);
	}
	
	
	public void updatePlayerShape(Rectangle playerShape) {
		this.playerShape = playerShape;
	}

	public void spawnNewLocation() {
		int bWidth = GameCanvas.borderWidth;
		int newX = rand.nextInt((GameCanvas.WIDTH - bWidth) + bWidth + 1) - bWidth;
		int newY = rand.nextInt((GameCanvas.HEIGHT - bWidth) + bWidth + 1) - bWidth;
		
		setX(newX);
		setY(newY);
	}

	@Override
	public int getBaseSpeed() {
		return baseSpeed;
	}

	@Override
	public void render(Graphics2D g2) {
		if (!GamePanelListener.showHitBoxes) {
			g2.setColor(new Color(0, 0, 0, 0));
		}
		else 
			g2.setColor(color);
			
		g2.drawImage(getImage(), null, x, y);
		g2.fill(entityShape);
	}

	@Override
	public void update() {
		if (playerShape.intersects(entityShape)) {
			Player.hasPower = true;
			setX(-100);
			setY(-100);
		}
	}

	@Override
	public void updateShape() {
		entityShape = new Rectangle(x + 8, y + 8, ImageStore.potXY[0] - 15, ImageStore.potXY[1] - 10);
	}
	
}
