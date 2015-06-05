package gameJSetting;

import game.Othello;
import gameMain.GameMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener, ActionListener {
	private Othello othello;
	private int[][] board;
	private int posX,posY;
	private int cellSize;
	private int flag;
	private JButton button;
	private String mode;
	private JFrame gFrame,frame;
	private Random rad;
	private int whiteFlag, blackFlag;
	private String whiteString, blackString;
	public GamePanel(String mode,JFrame gFrame,JFrame frame) {
		rad = new Random();
		whiteFlag = rad.nextInt(4);
		blackFlag = rad.nextInt(4);
		this.mode = mode;
		this.gFrame = gFrame;
		this.frame = frame;
		setBounds(0, 0, 350, 400);
		othello = new Othello();
		board = othello.getBoard();
		posX = 50;
		posY = 50;
		cellSize = 30;
		addMouseListener(this);
	}
	public void running(){
		if(mode == "easyAI"){
			whiteString = "easyAI";
			blackString = "Player";
			if(othello.getTurn() == othello.Turn_White){
				easyAI();
			}
		}else if(mode == "normalAI"){
			whiteString = "normalAI";
			blackString = "Player";
			if(othello.getTurn() == othello.Turn_White){
				normalAI();
			}
		}else if(mode == "hardAI"){
			whiteString = "hardAI";
			blackString = "Player";
			if(othello.getTurn() == othello.Turn_White){
				hardAI();
			}
		}else if(mode == "2P"){
			whiteString = "Player";
			blackString = "Player";
			System.out.println("MODE:2P");
		}else if(mode == "ultimateAI"){
			blackString = "Player";
			switch (flag) {
			case 0:
				whiteString = "easyAI";
				break;
			case 1:
				whiteString = "normalAI";
				break;
			case 2:
				whiteString = "hardAI";
				break;
			default:
				break;
			}
			if(othello.getTurn() == othello.Turn_White){
				ultimateAI();
			}
		}else if(mode == "DebugMode"){
			switch (whiteFlag) {
			case 0:
				whiteString = "easyAI";
				break;
			case 1:
				whiteString = "normalAI";
				break;
			case 2:
				whiteString = "hardAI";
				break;
			case 3:
				whiteString = "ultimateAI";
				break;
			default:
				break;
			}
			switch (blackFlag) {
			case 0:
				blackString = "easyAI";
				break;
			case 1:
				blackString = "normalAI";
				break;
			case 2:
				blackString = "hardAI";
				break;
			case 3:
				blackString = "ultimateAI";
				break;
			default:
				break;
			}
			if(othello.getTurn() == othello.Turn_White){
				flag = whiteFlag;
			}else if(othello.getTurn() == othello.Turn_Black){
				flag = blackFlag;
			}
			debugMode();
		}
	}
	private void easyAI(){
		int i,j,radX,radY;
		radX = rad.nextInt(board.length);
		radY = rad.nextInt(board[radX].length);
		i = radX;
		j = radY;
		def:while(true){
			while (j<board[i].length) {
				if(othello.setStone(i, j) > 0 || i == radX && j == radY - 1){
					break def;
				}
				j++;
			}
			j=0;
			i++;
			if(i == board.length){
				i=0;
			}
		}
	}
	private void normalAI(){
		ArrayList<int[]> setStoneArray;
		int max = 0;
		int[] maxPos;
		int[] setPos;
		setStoneArray = othello.getSetStoneArray();
		maxPos = setStoneArray.get(0);
		max = othello.getSetStoneChengeNum(maxPos[0], maxPos[1]);
		for(int i=1;i<setStoneArray.size();i++){
			setPos = setStoneArray.get(i);
			if(max < othello.getSetStoneChengeNum(setPos[0],setPos[1])){
				maxPos = setPos;
				max = othello.getSetStoneChengeNum(setPos[0],setPos[1]);
			}
		}
		othello.setStone(maxPos[0], maxPos[1]);
		repaint();
	}
	private void hardAI(){
		ArrayList<int[]> setStoneArray = new ArrayList<int[]>();
		int min;
		int[] minPos;
		int[] setPos;
		setStoneArray = othello.getSetStoneArray();
		minPos = setStoneArray.get(0);
		min = othello.getSetStoneChengeNum(minPos[0], minPos[1]);
		for(int i=1;i<setStoneArray.size();i++){
			setPos = setStoneArray.get(i);
			if(min > othello.getSetStoneChengeNum(setPos[0],setPos[1])){
				minPos = setPos;
				min = othello.getSetStoneChengeNum(setPos[0],setPos[1]);
			}
		}
		othello.setStone(minPos[0], minPos[1]);
		repaint();
	}
	private void ultimateAI(){
		flag = rad.nextInt(3);
		switch (flag) {
		case 0:
			easyAI();
			break;
		case 1:
			normalAI();
			break;
		case 2:
			hardAI();
			break;
		default:
			break;
		}
	}
	private void debugMode(){
		switch (flag) {
		case 0:
			easyAI();
			break;
		case 1:
			normalAI();
			break;
		case 2:
			hardAI();
			break;
		case 3:
			ultimateAI();
			break;
		default:
			break;
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setLayout(null);
		Color backColor;
		String turnString = "TURN";
		String winner = "WINNER";
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				if((i+j)%2 == 0){
					backColor = Color.GREEN;
				}else{
					backColor = Color.LIGHT_GRAY;
				}
				g.setColor(backColor);
				g.fillRect(posX+(cellSize*i), posY+(cellSize*j), cellSize, cellSize);
				if(board[i][j] == othello.Turn_Black){
					g.setColor(Color.BLACK);
					g.fillRoundRect(posX+(cellSize*i), posY+(cellSize*j), cellSize, cellSize, cellSize, cellSize);
				}else if(board[i][j] == othello.Turn_White){
					g.setColor(Color.WHITE);
					g.fillRoundRect(posX+(cellSize*i), posY+(cellSize*j), cellSize, cellSize, cellSize, cellSize);
				}
			}
		}
		if(othello.getTurn() == othello.Turn_White){
			turnString = "WHITE_TURN";
		}else if(othello.getTurn() == othello.Turn_Black){
			turnString = "BLACK_TURN";
		}
		g.setColor(Color.BLACK);
		g.drawString(turnString, 100, 10);
		g.drawString("White:" + whiteString, 100, 330);
		g.drawString("Black:" + blackString, 100, 360);
		if(othello.isFinish()){
			if(othello.winner() == othello.Turn_Black){
				winner = "BLACK";
			}else if(othello.winner() == othello.Turn_White){
				winner = "WHITE";
			}
			g.drawString("WIN:" + winner, 100, 30);
			button = new JButton("戻る");
			setButton();
			button.addActionListener(this);
			add(button);
		}
	}
	private void setButton() {
		button.setBounds(70, 30, 150, 30);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();

		System.out.println(""+othello.setStone((x-posX)/cellSize, (y-posY)/cellSize)+" "+(x-posX)/cellSize+""+(y-posY)/cellSize);
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("戻る")){
			gFrame.setVisible(false);
			frame.setVisible(true);
			gFrame.remove(this);
		}
	}
}
