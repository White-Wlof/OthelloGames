package mainJSetting;

import gameMain.GameMain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener,ItemListener {
	private JComboBox<String> modeList;
	private JButton button;
	private String mode;
	private BufferedImage image;
	private String imagePath;
	private JFrame frame;
	public MainPanel(JFrame frame) {
		this.frame = frame;
		setBounds(0, 0, 350, 350);
		imagePath = "images/othelloTitle.png";
		mode = "2P";
		Vector<String> modeNameVec = new Vector<String>();
	    modeNameVec.add("2P");
	    modeNameVec.add("easyAI");
	    modeNameVec.add("normalAI");
	    modeNameVec.add("hardAI");
	    modeNameVec.add("ultimateAI");
	    modeNameVec.add("DebugMode");
	    DefaultComboBoxModel<String> modeModel = new DefaultComboBoxModel<String>(modeNameVec);
	    setVisible(true);
		setLayout(null);
		imageAdjustment(320,180);
		modeList = new JComboBox<String>(modeModel);
		setModeList();
		modeList.addItemListener(this);
		button = new JButton("開始");
		setButton();
		button.addActionListener(this);
		add(modeList);
		add(button);
		reload();
	}
	private void reload(){
//		setVisible(false);
//		setVisible(true);
		revalidate();
	}
	private void imageAdjustment(int i, int j){
		image = null;
		Image icon = new ImageIcon(imagePath).getImage();
		image = new BufferedImage(icon.getWidth(null),icon.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(icon,0,0, i, j, null);
		g.dispose();
	}
	private void setModeList() {
		modeList.setBounds(10, 230, 140, 50);
		modeList.setPreferredSize(new Dimension(130, 30));
		modeList.setMaximumRowCount(6);
	}
	private void setButton() {
		button.setBounds(10, 280, 100, 30);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("開始")){
			GameMain main = new GameMain(mode);
			frame.setVisible(false);
			main.runGameMain(frame);
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		String modeName;
		if (e.getStateChange() == ItemEvent.SELECTED){
			modeName = (String)modeList.getSelectedItem();
			mode = modeName;
			System.out.println(mode);
			reload();
			repaint();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image, 10, 10, null);
		g.drawString("オセロゲームモード選択", 10, 210);
		g.drawString("GameMode:" + mode, 10, 230);
	}
}