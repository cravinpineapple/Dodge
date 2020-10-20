package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import controller.Key;
import model.images.ImageStore;
import view.GameCanvas;

public class Player extends Entity {

	private int damage = 1;
	public static boolean hasPower = false;
	private int powerUpTimerCount = 0;
	//private Random rand = new Random();

	private Timer powerUpTimer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			powerUpTimerCount++;
			
			if (powerUpTimerCount == 5) {
				hasPower = false;
				powerUpTimerCount = 0;
				powerUpTimer.stop();
			}
		}
	});

	public void resetTimer() {
		powerUpTimer.stop();
		powerUpTimerCount = 0;
		hasPower = false;
	}

	public Player() {
		super();
	}

	public Player(int speedMult, int x, int y, Color color, double sizeMult) {
		super(speedMult, x, y, color, sizeMult);

		setImage(ImageStore.wizardRight);
		entityShape = new Rectangle(x + 10, y + 20, currentWidth - 20, currentHeight - 10);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Timer getPowerUpTimer() {
		return powerUpTimer;
	}

	@Override
	public int getBaseSpeed() {
		return baseSpeed;
	}

	@Override
	public void render(Graphics2D g2) {
		if (hasPower) {
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
		entityShape = new Rectangle(x + 10, y + 20, currentWidth - 20, currentHeight - 10);
	}

	@Override
	public void update() {
		if (hasPower) {
			powerUpTimer.start();
		}

		if (!collision()) {
			if (Key.up.isDown && Key.right.isDown)  {
				x = getX() + getSpeed();
				y = getY() - getSpeed();
				setX(x);
				setY(y);
			}
			else if (Key.up.isDown && Key.left.isDown) {
				x = getX() - getSpeed();
				y = getY() - getSpeed();
				setX(x);
				setY(y);
			}
			else if (Key.down.isDown && Key.right.isDown) {
				x = getX() + getSpeed();
				y = getY() + getSpeed();
				setX(x);
				setY(y);
			}
			else if (Key.down.isDown && Key.left.isDown) {
				x = getX() - getSpeed();
				y = getY() + getSpeed();
				setX(x);
				setY(y);
			}
			else if (Key.up.isDown) {
				y = getY() - getSpeed();
				setY(y);
			}
			else if (Key.down.isDown) {
				y = getY() + getSpeed();
				setY(y);
			}
			else if (Key.left.isDown) {
				x = getX() - getSpeed();
				setX(x);
			}
			else if (Key.right.isDown) {
				x = getX() + getSpeed();
				setX(x);
			}
		}
		else {
			int gHeight = GameCanvas.HEIGHT;
			int gWidth = GameCanvas.WIDTH;
			int borderWidth = GameCanvas.borderWidth;
			if (y <= borderWidth)
				setY(borderWidth);
			if (y >= gHeight - borderWidth - height)
				setY(gHeight - borderWidth - height);
			if (x <= borderWidth)
				setX(borderWidth);
			if (x >= gWidth - borderWidth - width)
				setX(gWidth - borderWidth - width);
		}
	}

	private boolean collision() {
		Rectangle[] borders = GameCanvas.getBorders();
		Rectangle player = new Rectangle(x, y, width, height);

		boolean collisionLeft = borders[0].intersects(player);
		boolean collisionTop = borders[1].intersects(player);
		boolean collisionRight = borders[2].intersects(player);
		boolean collisionBot = borders[3].intersects(player);

		return (collisionLeft || collisionTop || collisionRight || collisionBot) ? true : false;
	}
	
}

/*

*/