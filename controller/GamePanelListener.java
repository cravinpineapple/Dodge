package controller;

import java.awt.event.KeyListener;

import model.images.ImageStore;
import view.GamePanel;

import java.awt.event.KeyEvent;


public class GamePanelListener implements KeyListener {

	boolean showHitBoxes = false;

	GamePanel gamePanel;

	public GamePanelListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// if (Key.up.isDown || Key.down.isDown || Key.left.isDown || Key.right.isDown)
		// 	return;
			
		var key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_W:
				Key.up.isDown = true;
				break;
			case KeyEvent.VK_A:
				Key.left.isDown = true;
				gamePanel.getPlayer().setImage(ImageStore.wizardLeft);
				break;
			case KeyEvent.VK_S:
				Key.down.isDown = true;
				break;
			case KeyEvent.VK_D:
				gamePanel.getPlayer().setImage(ImageStore.wizardRight);
				Key.right.isDown = true;
				break;
			case KeyEvent.VK_L:
				showHitBoxes = !showHitBoxes;
				if (showHitBoxes) {
					//gamePanel.
				}


		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		var key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_W:
				Key.up.isDown = false;
				break;
			case KeyEvent.VK_A:
				Key.left.isDown = false;
				break;
			case KeyEvent.VK_S:
				Key.down.isDown = false;
				break;
			case KeyEvent.VK_D:
				Key.right.isDown = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	
}
