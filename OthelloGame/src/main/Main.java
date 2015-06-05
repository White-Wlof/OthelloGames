package main;

import javax.swing.JFrame;
import mainJSetting.MainFrame;
import mainJSetting.MainPanel;

public class Main {
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame("オセロメニュー");
		MainPanel mainPanel = new MainPanel(mainFrame);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.add(mainPanel);
	}
}