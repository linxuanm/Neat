package cn.davidma.neat.logging;

/**
 * The logger is responsible for debugging and
 * error reporting, as well as generating a crash
 * report when configured to.
 * 
 * @author David Ma
 */
public class Logger {

	private MsgType defaultLevel = MsgType.INFO;
	
	/**
	 * Creates the logger with the specified
	 * configurations.
	 */
	public Logger() {
		
	}
	
	/**
	 * Logs a message at the {@link #defaultLevel}.
	 * 
	 * @param msg The message to be logged.
	 */
	public void log(String msg) {
		this.log(msg, this.defaultLevel);
	}
	
	/**
	 * Logs a message at the given level.
	 * 
	 * @param msg The message to be logged.
	 * @param level The level at which the message is logged.
	 */
	public void log(String msg, MsgType level) {
		
	}
}
