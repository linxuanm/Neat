package cn.davidma.neat.logging;

import java.util.List;
import java.util.function.Consumer;

/**
 * This enum contains all the level of debugging
 * messages.
 * 
 * @author David Ma
 */
public enum MsgType {

	/**
	 * Logs debugging messages.
	 */
	INFO("INFO", System.out::println),
	
	/**
	 * Indicates potential problems. Can be but
	 * should not be ignored.
	 */
	WARNING("WARNING", System.err::println),
	
	/**
	 * Something went wrong. A thread might encountered
	 * some problems, like fetching update from the
	 * internet failed. The game might still be able to
	 * run even if such error occured.
	 */
	ERROR("ERROR", System.err::println),
	
	/**
	 * All hell is lost. Logging messages at this level
	 * will cause a forceful crash. This might be used to
	 * prevent further corruption to a game save file when
	 * something went wrong, or just generally for errors
	 * that causes major problems.
	 */
	FATAL("FATAL", System.err::println);
	
	/**
	 * The prefix of the printed message in the
	 * format of [Neat/{prefix}].
	 */
	private String prefix;
	/**
	 * The method used to print the message to
	 * the console. 
	 */
	private Consumer<String> stdout;
	
	/**
	 * Creates a message type level.
	 * 
	 * @param prefix The prefix of the printed message.
	 * @param stdout The method used to print the message to the console.
	 */
	private MsgType(String prefix, Consumer<String> stdout) {
		this.prefix = prefix;
		this.stdout = stdout;
	}
	
	/**
	 * Formats the given message with the message
	 * type's template (adds prefix, etc).
	 * 
	 * @param message The message to be formatted.
	 * @return The foormatted message.
	 */
	public String format(String message) {
		return String.format("[Neat/%s] %s", this.prefix, message);
	}
	
	/**
	 * Prints the given message to the console.
	 * 
	 * @param message The message (formatted) to be printed.
	 */
	public void print(String message) {
		this.stdout.accept(message);
	}
	
	/**
	 * Prints the given messages to the console.
	 * 
	 * @param messages The messages (formatted) to be printed.
	 */
	public void print(List<String> messages) {
		for (String message: messages) {
			this.print(message);
		}
	}
}