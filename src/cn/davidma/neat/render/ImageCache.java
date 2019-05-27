package cn.davidma.neat.render;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * This class caches the Image of each GameObject so that no new Image is initialized per GameObject.
 * 
 * @author David Ma
 */
public class ImageCache {
	
	private static Map<String, Image> imageCache = new HashMap<String, Image>();
	
	/**
	 * Loads and returns an Image based on the given path.
	 * 
	 * @param path The path of the Image.
	 * @return The Image.
	 */
	public static Image loadImage(String path) {
		if (imageCache.containsKey(path)) {
			return imageCache.get(path);
		}
		
		Image image = new Image(path);
		imageCache.put(path, image);
		return image;
	}
	
	/**
	 * Clears the cache.
	 */
	public static void clearCache() {
		imageCache.clear();
	}
}
