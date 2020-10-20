package model.images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.io.File;

import javax.imageio.ImageIO;

import view.GamePanel;

public class ImageStore {

	public static BufferedImage orcLeft;
	public static BufferedImage orcRight;
	public static BufferedImage wizardLeft;
	public static BufferedImage wizardRight;
	public static BufferedImage potion;

	public static int[] orcXY = {80, 80};
	public static int[] wizXY = {50, 65};
	public static int[] potXY = {40, 40};
	
	
	// executed before main method
	static {
		String cwd = System.getProperty("user.dir");
		System.out.println("cwd: " + cwd);
		orcLeft = readImage("model/images/orcLeft.png", (int) (orcXY[0] * GamePanel.sizeSetting), (int) (orcXY[1] * GamePanel.sizeSetting));
		orcRight = readImage("model/images/orcRight.png", (int) (orcXY[0] * GamePanel.sizeSetting), (int) (orcXY[1] * GamePanel.sizeSetting));
		wizardLeft = readImage("model/images/wizardLeft.png", wizXY[0], wizXY[1]);
		wizardRight = readImage("model/images/wizardRight.png", wizXY[0], wizXY[1]);
		potion = readImage("model/images/potion.png", potXY[0], potXY[1]);
	}

	// reads from file and scales to width & height
	public static BufferedImage readImage(String path, int width, int height) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(path));
			Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = resizedImage.createGraphics();
			g2.drawImage(tmp, 0, 0, null);
			g2.dispose();
			return resizedImage;
		} catch (Exception e) {
			System.out.println("Image file load error");
			System.out.println("e: " + e.getMessage());
		}

		return null;
	}

}