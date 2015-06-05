package gameMain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import game.GameEngine;

public class GameMain {
	private String mode;
	private JFrame frame;
	public GameMain(String mode){
		this.mode = mode;
	}
	public void runGameMain(JFrame frame){
		this.frame = frame;
		GameEngine engine = new GameEngine(mode,frame);
		ExecutorService ES = Executors.newSingleThreadExecutor();
		ES.submit(engine);
	}
}