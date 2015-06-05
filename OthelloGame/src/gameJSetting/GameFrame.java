package gameJSetting;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame(String title) {
		setTitle(title);
		setSize(350, 400);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}