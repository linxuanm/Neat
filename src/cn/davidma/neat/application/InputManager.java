package cn.davidma.neat.application;

import java.awt.MouseInfo;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import javafx.scene.input.MouseButton;

/**
 * A static handler that handles all input events.
 * 
 * @author David Ma
 */
public class InputManager {
	
	private static Set<String> keys = new HashSet<String>();
	private static boolean leftMouseDown;
	private static boolean rightMouseDown;
	private static Consumer<String> keyDownHandler;
	private static Consumer<String> keyUpHandler;
	private static Consumer<MouseEvent> mouseDownHandler;
	private static Consumer<MouseEvent> mouseUpHandler;
	
	public static boolean isKeyDown(String keyCode) {
		return keys.contains(keyCode);
	}
	
	public static boolean isMouseDown(MouseKey mouseKey) {
		return mouseKey == MouseKey.LEFT ? leftMouseDown : rightMouseDown;
	}
	
	public static void setKeyDown(String keyCode) {
		keys.add(keyCode);
		if (keyDownHandler != null) {
			keyDownHandler.accept(keyCode);
		}
	}
	
	public static void setKeyUp(String keyCode) {
		keys.remove(keyCode);
		if (keyUpHandler != null) {
			keyUpHandler.accept(keyCode);
		}
	}
	
	public static void setMouseDown(MouseEvent mouseEvent) {
		if (mouseEvent.mouseKey == MouseKey.LEFT) leftMouseDown = true;
		else rightMouseDown = true;
		
		if (mouseDownHandler != null) {
			mouseDownHandler.accept(mouseEvent);
		}
	}
	
	public static void setMouseUp(MouseEvent mouseEvent) {
		if (mouseEvent.mouseKey == MouseKey.LEFT) leftMouseDown = false;
		else rightMouseDown = false;
		
		if (mouseUpHandler != null) {
			mouseUpHandler.accept(mouseEvent);
		}
	}
	
	public static void setKeyDownHandler(Consumer<String> keyDownHandler) {
		InputManager.keyDownHandler = keyDownHandler;
	}
	
	public static void setKeyUpHandler(Consumer<String> keyUpHandler) {
		InputManager.keyUpHandler = keyUpHandler;
	}
	
	public static void setMouseDownHandler(Consumer<MouseEvent> mouseDownHandler) {
		InputManager.mouseDownHandler = mouseDownHandler;
	}
	
	public static void setMouseUpHandler(Consumer<MouseEvent> mouseUpHandler) {
		InputManager.mouseUpHandler = mouseUpHandler;
	}
	
	/**
	 * Gets the x position of the mouse relative to the window.
	 * 
	 * @return The x position of the mouse.
	 */
	public static int getMouseX() {
		return MouseInfo.getPointerInfo().getLocation().x - NeatGame.getInstance().getWindowX();
	}
	
	/**
	 * Gets the y position of the mouse relative to the window.
	 * 
	 * @return The y position of the mouse.
	 */
	public static int getMouseY() {
		return MouseInfo.getPointerInfo().getLocation().y - NeatGame.getInstance().getWindowY();
	}
	
	/**
	 * An instance of this class holds information about a mouse click.
	 * 
	 * @author David Ma
	 */
	public static class MouseEvent {
		public MouseKey mouseKey;
		public int x;
		public int y;
		
		public MouseEvent(MouseKey mouseKey, int x, int y) {
			this.mouseKey = mouseKey;
			this.x = x;
			this.y = y;
		}
		
		public MouseEvent(javafx.scene.input.MouseEvent event) {
			this.mouseKey = MouseKey.None;
			if (event.getButton() == MouseButton.PRIMARY) mouseKey = MouseKey.LEFT;
			else if (event.getButton() == MouseButton.SECONDARY) mouseKey = MouseKey.RIGHT;
			this.x = (int) event.getSceneX();
			this.y = (int) event.getSceneY();
		}
	}
	
	public enum MouseKey {
		LEFT, RIGHT, None;
	}
}
