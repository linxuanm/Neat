# Neat
Neat is ~~a mod by Vazkii~~ a beginner-friendly library aimed at making game programming easier.

Designed for educational purposes.

## Main Class
The main game class, used for handling windows, displays, and game scenes is [NeatGame](src/cn/davidma/neat/application/NeatGame.java). This is the main application from which games inherit.

The main program should extend this class. All setup should be done in the ```NeatGame#setup``` method. This class should not be explicitly instantiated. The game is launched with ```NeatGame::launch```.

An example override of ```NeatGame#setup``` is shown below:
```java
@Override
protected void setup() {
	this.setTitle("My Game");
	this.setSize(1000, 200);
	this.setBackgroundColor("#000000");
}
```

This class extends javafx.application.Application, from which directly invoking methods and editing values are possible, but not recommended.

### Tips
- All setup methods return "this" instance to allow chaining.
- If NeatGame#setSize is never set, the game window will automatically fill the screen.

### Example Usage
```java
public class ExampleGame extends NeatGame {
	public static void main(String[] args) {		
		launch(args);
	}
	
	protected void setup() {
		this.setTitle("My Game");
		this.setSize(1000, 200);
		this.setBackgroundColor("#000000");
	}
}
```