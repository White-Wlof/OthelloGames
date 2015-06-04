package gameJSetting;

import game.Othello;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {
	private Othello othello;
	private int[][] board;
	private int posX,posY;
	private int cellSize;
	public GamePanel() {
		setBounds(0, 0, 600, 600);
		othello = new Othello();
		board = othello.getBoard();
		posX = 50;
		posY = 50;
		cellSize = 30;
		addMouseListener(this);
	}
	public void running(){
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color backColor;
		String turnString = "TURN";
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
					turnString = "BLACK_TURN";
					g.fillRoundRect(posX+(cellSize*i), posY+(cellSize*j), cellSize, cellSize, cellSize, cellSize);
				}else if(board[i][j] == othello.Turn_White){
					g.setColor(Color.WHITE);
					turnString = "WHITE_TURN";
					g.fillRoundRect(posX+(cellSize*i), posY+(cellSize*j), cellSize, cellSize, cellSize, cellSize);
				}
			}
		}
//		g.setColor(Color.BLACK);
//		g.drawString(turnString, 100, 10);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();
		othello.setStone((x-posX)/cellSize, (y-posY)/cellSize);
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
}
