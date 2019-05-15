package cn.davidma.neat.application;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A static handler that handles all input events.
 * 
 * @author David Ma
 */
public class InputHandler {
	
	private static Set<String> keys = new HashSet<String>();
	private static Consumer<String> keyDownHandler;
	private static Consumer<String> keyUpHandler;
	
	public static boolean isKeyDown(String keyCode) {
		return keys.contains(keyCode);
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
	
	public static void setKeyDownHandler(Consumer<String> keyDownHandler) {
		InputHandler.keyDownHandler = keyDownHandler;
	}
	
	public static void setKeyUpHandler(Consumer<String> keyUpHandler) {
		InputHandler.keyUpHandler = keyUpHandler;
	}
}
