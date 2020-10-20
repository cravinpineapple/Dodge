package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.GamePanelListener;
import model.images.ImageStore;
import view.GameCanvas;
import view.MenuScreen;

public class Enemy extends Entity {

	private int damage = 1;

	private Rectangle playerShape;

	// these variables handle enemy collision
	private int direction; // 0 = Up     1 = Up-Right    2 = Right   3 = Down-Right,
			   			   // 4 = Down   5 = Down-Left   6 = Left    7 = Up-Left
	private Random rand = new Random();
	private int topBounce[] = {3, 4, 5};
	private int botBounce[] = {0, 1, 7};
	private int rightBounce[] = {5, 6, 7};
	private int leftBounce[] = {1, 2, 3};

	public Enemy() {
		super();
		direction = rand.nextInt(8);
		updatePictureDirection(direction);
	}

	public Enemy(int speedMult, int x, int y, Color color, double sizeMult) {
		super(speedMult, x, y, color, sizeMult);
		direction = rand.nextInt(8);
		updatePictureDirection(direction);

		hbX = x + 20;
		hbY = y + 20;

		setImage(ImageStore.orcRight);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void updatePlayerShape(Rectangle playerShape) {
		this.playerShape = playerShape;
	}

	@Override
	public int getBaseSpeed() {
		return baseSpeed;
	}

	@Override
	public void render(Graphics2D g2) {
		if (!GamePanelListener.showHitBoxes) {
			// g2.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			g2.setColor(new Color(0, 0, 0, 0));
		}
		else 
			g2.setColor(color);

		g2.drawImage(getImage(), null, x, y);
		g2.fill(entityShape);
	}

	@Override
	public void updateShape() {
		hbX = x + 20;
		hbY = y + 20;
		entityShape = new Rectangle(hbX, hbY, hbWidth, hbHeight);
	}

	@Override
	public void update() {
		if (!collision()) {
			moveDirection();
		}
		else {
			int gHeight = GameCanvas.HEIGHT;
			int gWidth = GameCanvas.WIDTH;
			int borderWidth = GameCanvas.borderWidth;
			if (playerShape.intersects(entityShape)) {
				if (Player.hasPower) {
					moveDirection();
					return;
				}
				JFrame window = gamePanel.getWindow();
				JOptionPane.showMessageDialog(window, "Game Over! You survived: " + GameContainer.score + " seconds!");
				gamePanel.getPlayer().resetTimer();

				window.getContentPane().removeAll();
				var menu = new MenuScreen(window);
				menu.init();
				window.pack();
				window.revalidate();
				gamePanel.getGc().stop();
			}
			if (hbY <= borderWidth) {
				setHbY(borderWidth); // was set y
				direction = topBounce[rand.nextInt(3)];
			}
			if (hbY >= gHeight - borderWidth - hbHeight) {
				setHbY(gHeight - borderWidth - hbHeight);
				direction = botBounce[rand.nextInt(3)];
			}
			if (hbX <= borderWidth) {
				setHbX(borderWidth); // was set x
				direction = leftBounce[rand.nextInt(3)];
			}
			if (hbX >= gWidth - borderWidth - hbWidth) {
				setHbX(gWidth - borderWidth - hbWidth);
				direction = rightBounce[rand.nextInt(3)];
			}

			updatePictureDirection(direction);
		}
	}

	private void updatePictureDirection(int direction) {
		if (direction > 4)
				setImage(ImageStore.orcLeft);
			else
				setImage(ImageStore.orcRight);
	}

	private void moveDirection() {
		switch (direction) {
			case 0:
				setY(y - getSpeed());
				break;
			case 1:
				setX(x + getSpeed());
				setY(y - getSpeed());
				break;
			case 2:
				setX(x + getSpeed());
				break;
			case 3:
				setX(x + getSpeed());
				setY(y + getSpeed());
				break;
			case 4:
				setY(y + getSpeed());
				break;
			case 5:
				setX(x - getSpeed());
				setY(y + getSpeed());
				break;
			case 6:
				setX(x  - getSpeed());
				break;
			case 7:
				setX(x - getSpeed());
				setY(y - getSpeed());
				break;
		}
	}

	private boolean collision() {
		Rectangle[] borders = GameCanvas.getBorders();
		
		boolean collisionPlayer = playerShape.intersects(entityShape);
		boolean collisionLeft = borders[0].intersects(entityShape);
		boolean collisionTop = borders[1].intersects(entityShape);
		boolean collisionRight = borders[2].intersects(entityShape);
		boolean collisionBot = borders[3].intersects(entityShape);

		return (collisionLeft || collisionTop || collisionRight || collisionBot || collisionPlayer) ? true : false;
	}
}
