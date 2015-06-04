package gameMain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import game.GameEngine;

public class GameMain {
	public GameMain(){
		System.out.println("allGameMain");
	}
	public void runGameMain(){
		GameEngine engine = new GameEngine();
		ExecutorService ES = Executors.newSingleThreadExecutor();
		ES.submit(engine);
	}
}