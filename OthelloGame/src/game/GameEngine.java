package game;

import gameJSetting.GameFrame;
import gameJSetting.GamePanel;

import java.awt.Color;

import javax.swing.JFrame;

public class GameEngine implements Runnable {
	private GamePanel gamePanel;
	private String mode;
	private JFrame frame;
	public GameEngine(String mode,JFrame frame) {
		this.mode = mode;
		this.frame = frame;
		GameFrame gameFrame = new GameFrame("オセロ");
		gamePanel = new GamePanel(mode,gameFrame,frame);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.add(gamePanel);
	}
	@Override
	public void run() {
		while(true){
			gamePanel.running();
			gamePanel.repaint();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}