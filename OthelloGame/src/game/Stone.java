package game;

import java.awt.Color;
import java.awt.Graphics;

public class Stone {
	private int posX;
	private int posY;
	private int size;
	private int time;
	private int timemax;
	private boolean animation;
	private Color beforeColor;
	private Color afterColor;
	public boolean setVisible;
	public Stone() {
		posX = 0;
		posY = 20;
		timemax = 30;
		size = 20;
		setVisible = false;
	}
	public Stone(int x, int y, int size, Color c) {
		this();
		posX = x;
		posY = y;
		this.size = size;
		beforeColor = c;
	}
	public void Chenge(Color color){
		afterColor = color;
		time = timemax * 2;
		animation = true;
	}
	public void refresh(){
		if(setVisible)
		if(animation){
			time--;
			if(time == timemax){
				beforeColor = afterColor;
			}
			if(time < 1){
				animation = false;
			}
		}
	}
	public void setVisible(boolean B){
		setVisible = B;
	}
	public void show(Graphics g){
		if(!setVisible)
		return;
		double d = ((double)(time-timemax)/timemax);
		d *= d;
		g.setColor(beforeColor);
		g.fillRoundRect(posX+((size-((int)(size*d)))/2), posY, (int)(size*d), size, (int)(size*d), size);
	}
}
