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
	
	@Override
	protected void setup() {
		this.setTitle("My Game");
		this.setSize(1000, 200);
		this.setBackgroundColor("#000000");
	}

	@Override
	protected void gameStart() {
		// Add scenes and objects, etc.
	}
}
```

## Objects
Neat provides a variety of __objects__ to work with in order to simplify the process of creating a game. Each __sprite__ (occasionally refered to as "render") in game, such as image, text, etc, are all objects.

### SceneObject
__SceneObject__ refers to objects that can be added to a game (specifically a [GameScene](#SceneManaging), which will be discussed later). SceneObject is an basic class that represents an object on screen, and hence have attributes such as location, rotation and size. Some basic operations can be found below:
- ```SceneObject#(get/set)X```
- ```SceneObject#(get/set)Y```
- ```SceneObject#(get/set)Rotation```
- ```SceneObject#(get/set)Opacity```

Note that all setters return "this" instance to allow chaining (i.e. ```mySceneObject.setX(100).setY(100).setScaleX(400).setScaleY(200);```).

## Scene Managing
To ease the managing of collections of objects, Neat has a scene managing system.

A [GameScene](src/cn/davidma/neat/layout/GameScene.java) is a group of [SceneObjects](src/cn/davidma/neat/object/SceneObject.java).

Each game can only have __one__ GameScene active at the same time, and each SceneObject can only belong to __one__ GameScene. The sole purpose of the GameScene system is to create a way to completely separate the logic of objects, such as a menu scene and a main game scene. For handling objects as collections, see [Group](#Group).

When a scene is not active, all operations, including rendering, updating and callbacking, of its SceneObjects will be stopped.

To make a GameScene active, call ```NeatGame#setScene```; to add a SceneObject to a GameScene, call ```GameScene#addChild```.

## Group
A [Group](src/cn/davidma/neat/layout/Group.java) is a collection of SceneObjects. A Group can be used to manage SceneObjects, as well as to apply certain actions to all SceneObjects in the Group.