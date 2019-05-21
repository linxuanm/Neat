package cn.davidma.neat.util;

/**
 * This class manipulates paths.
 * 
 * @author David Ma
 */
public class PathUtil {
	
	public static String fromRelativePath(Class<?> clazz, String path) {
		return clazz.getResource(path).toExternalForm();
	}
}
