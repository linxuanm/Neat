package test;

import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.layout.GameScene;

public class ExampleGame extends NeatGame {
	
	public GameScene mainScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	protected void setup() {
		this.setTitle("My Game");
		this.setBackgroundColor("#E7E7E7");
		this.setSize(1300, 700);
	}

	@Override
	protected void gameStart() {
		this.mainScene = new GameScene();
		this.mainScene.addChild(new ExampleObject());
		this.setScene(this.mainScene);
	}
}
