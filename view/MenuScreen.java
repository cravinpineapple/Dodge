package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuScreen {

	private JFrame window;

	public MenuScreen(JFrame window) {
		this.window = window;
		window.setLocation(500, 200);
		window.setSize(new Dimension(250, 150));
	}

	public void init() {
		Container cp = window.getContentPane();
		//cp.setLayout(new GridLayout(2, 1));

		ButtonGroup difficulty = new ButtonGroup();
		JRadioButton easyButton = new JRadioButton("Easy");
		JRadioButton medButton = new JRadioButton("Medium");
		JRadioButton hardButton = new JRadioButton("Hard");
		medButton.setSelected(true);
		difficulty.add(easyButton);
		difficulty.add(medButton);
		difficulty.add(hardButton);

		JButton goButton = new JButton("Go!");

		JPanel panel1 = new JPanel();
		panel1.add(easyButton);
		panel1.add(medButton);
		panel1.add(hardButton);
		JPanel panel2 = new JPanel();
		panel2.add(goButton);
		cp.add(BorderLayout.CENTER, panel1);
		cp.add(BorderLayout.SOUTH, panel2);

		goButton.addActionListener(e -> {
			window.getContentPane().removeAll();
			var gamePanel = new GamePanel(window);
			gamePanel.init();
			window.pack();
			window.revalidate();
			gamePanel.getGc().start();
		});

		easyButton.addActionListener (e -> {
			GamePanel.sizeSetting = 0.5;
			GamePanel.speedSetting = -8;
		});
		
		medButton.addActionListener (e -> {
			GamePanel.sizeSetting = 1;
			GamePanel.speedSetting = -5;
		});
		hardButton.addActionListener (e -> {
			GamePanel.sizeSetting = 1.5;
			GamePanel.speedSetting = -2;
		});

	}
	
}
