package cn.davidma.neat.application;

import java.util.function.Consumer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The main application from which games extend.
 * 
 * <p><b>Usage</b></p>
 * 
 * <p>
 * The main program should extend this class. All setup
 * should be done in the  method. This class should
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
	
	private Group group;
	private Scene scene;
	
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
		this.backgroundColor = new Color(red, green, blue, 1);
		
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
	 * Internal method that launches the game.
	 */
	@Override
	public final void start(Stage stage) throws Exception {
		this.setup();
		
		Pane root = new Pane();
		root.getChildren().add(group = new Group());
		this.scene = new Scene(root, this.width, this.height, this.backgroundColor);
		
		stage.setScene(this.scene);
		stage.setTitle(this.title);
		stage.setResizable(false);
		stage.show();
	}
	
	/**
	 * Sets up all the configurations of the game.
	 * Should be overridden, but not mandatory.
	 */
	protected void setup() {
		
	}
}
