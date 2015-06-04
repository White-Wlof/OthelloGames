package gameJSetting;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame(String title) {
		setTitle(title);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}