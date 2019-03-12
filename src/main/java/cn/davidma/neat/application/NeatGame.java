package main.java.cn.davidma.neat.application;

import java.util.function.Consumer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
		setWindowConfiguration(game -> {
			game.setTitle("My Game");
			game.setSize(1000, 200);
		});
		
		launch(args);
	}
}
 * </code></pre>
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
	 * The setup method of this instance. Set the value through {@link #setWindowConfiguration(Consumer)}.
	 */
	private static Consumer<NeatGame> windowConfiguration;
	
	private Group group;
	private Scene scene;
	
	/**
	 * Sets the configuration function of the game window.
	 * 
	 * @param windowConfiguration A {@link java.util.function.Consumer} that invokes setup methods on this class.
	 */
	protected static void setWindowConfiguration(Consumer<NeatGame> windowConfiguration) {
		NeatGame.windowConfiguration = windowConfiguration;
	}
	
	/**
	 * Sets the size for the window.
	 * 
	 * @param width The width of the window.
	 * @param height The height of the window.
	 * @return This instance.
	 */
	public NeatGame setSize(int width, int height) {
		this.width = width;
		this.height = height;
		
		return this;
	}
	
	/**
	 * Sets the title for the window.
	 * 
	 * @param title The title of the window.
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
		if (windowConfiguration != null) windowConfiguration.accept(this);
		
		BorderPane root = new BorderPane();
		root.getChildren().add(group = new Group());
		this.scene = new Scene(root, this.width, this.height);
		
		stage.setScene(this.scene);
		stage.setTitle(this.title);
		stage.show();
	}
}
