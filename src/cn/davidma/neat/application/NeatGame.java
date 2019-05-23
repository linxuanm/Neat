package cn.davidma.neat.application;

import cn.davidma.neat.layout.GameScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The main application from which games extend.
 * 
 * <p><b>Usage</b></p>
 * 
 * <p>
 * The main program should extend this class. All setup
 * should be done in the {@link #setup()} method. This class should
 * not be explicitly instantiated. The game is launched
 * with {@link #launch(String...)}.
 * </p>
 * 
 * <p>
 * This class extends {@link javafx.application.Application},
 * from which directly invoking methods and editing values are
 * possible, but not recommended.
 * </p>
 * 
 * <p><b>Tips</b></p>
 * 
 * <ul>
 * <li>All setup methods return "this" instance to allow chaining.</li>
 * <li>If {@link #setSize(int, int)} is never set, the game window will automatically fill the screen.</li>
 * </ul>
 * 
 * <p><b>Example Code</b></p>
 * 
 * <p>Here is an example that creates a simple game window:</p>
 * <pre><code>
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
 * </code></pre>
 * 
 * @author David Ma
 */
public abstract class NeatGame extends Application {
	
	private static NeatGame instance;
	
	/**
	 * The dimension of the game window. Set the values through {@link #setSize(int, int)}.
	 */
	private int width, height;
	/**
	 * The title of the game window. Set the value through {@link #setTitle(String)}.
	 */
	private String title;
	/**
	 * The background color of the game window.
	 */
	private Color backgroundColor;
	/**
	 * The maximum delay between frames.
	 */
	private int delay = 20;
	
	/**
	 * The currently active GameScene.
	 */
	private GameScene gameScene;
	
	private Timeline timeline;
	private javafx.scene.Group group;
	private Stage stage;
	private Scene scene;
	
	public NeatGame() {
		super();
		instance = this;
	}
	
	/**
	 * Gets the currently active NeatGame.
	 */
	public static NeatGame getInstance() {
		return instance;
	}
	
	/**
	 * Allows the user to set up the scene when the game starts.
	 */
	protected abstract void gameStart();
	
	/**
	 * Gets the {@Link javafx.scene.Group} of this game.
	 * 
	 * @return The group of this game.
	 */
	public javafx.scene.Group getGroup() {
		return this.group;
	}
	
	/**
	 * Gets the width of the window.
	 * 
	 * @return The width of the window.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Gets the height of the window.
	 * 
	 * @return The height of the window.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Sets the size for the game window.
	 * 
	 * @param width The width of the game window.
	 * @param height The height of the game window.
	 * @return This instance.
	 */
	public NeatGame setSize(int width, int height) {
		this.width = width;
		this.height = height;
		
		return this;
	}
	
	/**
	 * Sets the background color for the game window.
	 * 
	 * @param hexColor The background color (hex values) of the game window.
	 * @return This instance.
	 */
	public NeatGame setBackgroundColor(String hexColor) {
		this.backgroundColor = Color.web(hexColor);
		
		return this;
	}
	
	/**
	 * Sets the background color for the game window.
	 * 
	 * @param red Value of red [0, 255].
	 * @param green Value of green [0, 255].
	 * @param blue Value of blue [0, 255].
	 * @return This instance.
	 */
	public NeatGame setBackgroundColor(int red, int green, int blue) {
		this.backgroundColor = new Color(red / 255D, green / 255D, blue / 255D, 1);
		
		return this;
	}
	
	/**
	 * Sets the title for the game window.
	 * 
	 * @param title The title of the game window.
	 * @return This instance.
	 */
	public NeatGame setTitle(String title) {
		this.title = title;
		
		return this;
	}
	
	/**
	 * Sets the delay between frames.
	 * 
	 * @param delay The delay between frames.
	 * @return This instance.
	 */
	public NeatGame setDelay(int delay) {
		this.delay = delay;
		
		return this;
	}
	
	/**
	 * Gets the currently active GameScene.
	 * 
	 * @return The currently active GameScene.
	 */
	public GameScene getScene() {
		return this.gameScene;
	}
	
	/**
	 * Sets the currently active GameScene.
	 * 
	 * @param gameScene The GameScene to be set to.
	 * @return This instance.
	 */
	public NeatGame setScene(GameScene gameScene) {
		this.group.getChildren().clear();
		this.gameScene = gameScene;
		this.gameScene.forEach(sceneObject -> {
			sceneObject.constructRender();
			this.group.getChildren().add(sceneObject.getRenderNode());
		});
		
		return this;
	}
	
	/**
	 * Internal method that launches the game.
	 */
	@Override
	public final void start(Stage stage) throws Exception {
		this.internalInitialization();
		this.setup();
		this.stage = stage;
		
		Pane root = new Pane();
		root.getChildren().add(group = new javafx.scene.Group());
		this.scene = new Scene(root, this.width, this.height, this.backgroundColor);
		
		this.scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
			InputManager.setKeyDown(key.getCode().toString());
		});
		
		this.scene.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
			InputManager.setKeyUp(key.getCode().toString());
		});
		
		stage.setScene(this.scene);
		stage.setTitle(this.title);
		stage.setResizable(false);
		stage.show();
		
		this.timeline.play();
		
		this.gameStart();
	}
	
	/**
	 * Internal setups.
	 */
	private final void internalInitialization() {
		this.timeline = new Timeline(new KeyFrame(Duration.millis(this.delay), event -> this.update()));
		this.timeline.setCycleCount(Animation.INDEFINITE);
	}
	
	/**
	 * Gets the x position of the window on screen.
	 * 
	 * @return The x position of the window.
	 */
	public final int getWindowX() {
		return (int) this.stage.getX();
	}
	
	/**
	 * Gets the y position of the window on screen.
	 * 
	 * @return The y position of the window.
	 */
	public final int getWindowY() {
		return (int) this.stage.getY();
	}
	
	/**
	 * Sets up all the configurations of the game window. Called before the window launches.
	 */
	protected abstract void setup();
	
	/**
	 * Used internally for updating the screen.
	 * 
	 * <p>
	 * This method can be overriden, in which case it
	 * <b>must</b> be called as a super method.
	 * </p>
	 */
	protected void update() {
		if (this.gameScene != null) {
			this.gameScene.forEach(sceneObject -> {
				sceneObject.update();
			});
		}
	}
}
