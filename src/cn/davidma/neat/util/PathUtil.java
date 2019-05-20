package cn.davidma.neat.util;

public class PathUtil {
	
	public static String fromRelativePath(Class<?> clazz, String path) {
		return clazz.getResource(path).toExternalForm();
	}
}
