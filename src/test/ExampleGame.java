package test;

import cn.davidma.neat.application.NeatGame;

public class ExampleGame extends NeatGame {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	protected void setup() {
		this.setTitle("My Game");
		this.setBackgroundColor("#7F7F7F");
		//this.setSize(100, 100);
	}
}
