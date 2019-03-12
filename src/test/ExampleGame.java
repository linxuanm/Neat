package test;

import main.java.cn.davidma.neat.application.NeatGame;

public class ExampleGame extends NeatGame {

	public static void main(String[] args) {
		setWindowConfiguration(neatGame -> {
			neatGame.setTitle("ExampleGame").setSize(500, 500).setBackgroundColor("#000000");
		});
		
		launch(args);
	}
}
