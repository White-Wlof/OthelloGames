package mainJSetting;

import gameMain.GameMain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {
	private JLabel titleLabel, sqlLabel;
	private JButton button;
	private String mode;
	public MainPanel() {
		mode = "2P";
		setBounds(0, 0, 300, 300);
//		setBackground(Color.BLACK);
		setVisible(true);
		setLayout(null);
		titleLabel = new JLabel("オセロゲームモード選択");
		setTitleLabel();
		sqlLabel = new JLabel("GameMode:" + mode);
		setSQLLabel();
		button = new JButton("開始");
		setButton();
		button.addActionListener(this);
		add(titleLabel);
		add(sqlLabel);
		add(button);
	}
	private void setTitleLabel() {
		titleLabel.setBounds(10, 10, 150, 30);
	}
	private void setSQLLabel() {
		sqlLabel.setBounds(10, 50, 100, 30);
	}
	private void setButton() {
		button.setBounds(10, 100, 100, 30);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("開始")){
			System.out.println("GameStart");
			GameMain main = new GameMain();
			main.runGameMain();
		}
	}
}