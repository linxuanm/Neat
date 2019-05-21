package cn.davidma.test;

import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.layout.GameScene;
import cn.davidma.neat.object.GameObject;

public class MainGame extends NeatGame {
	
	public GameObject platform;
	private GameScene mainScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	protected void setup() {
		this.setTitle("ExampleGame");
		this.setBackgroundColor("#FFFFFF");
		this.setSize(1000, 800);
	}
	
	@Override
	protected void gameStart() {
		this.mainScene = new GameScene();
		this.setScene(this.mainScene);
		this.mainScene.addChild(new Ball());
		this.mainScene.addChild(this.platform = new Platform());
	}
}
