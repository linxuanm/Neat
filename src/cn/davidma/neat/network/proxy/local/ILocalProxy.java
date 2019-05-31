package cn.davidma.neat.network.proxy.local;

import java.io.IOException;

import cn.davidma.neat.network.proxy.IProxy;

/**
 * This interface marks that a class is a representation of this device based on the type of connection.
 * 
 * <p>
 * An instance of this is responsible for the main server/client receiving loop on a thread.
 * </p>
 * 
 * @author David Ma
 */
public interface ILocalProxy extends IProxy {
	
	/**
	 * Starts the receiving loop.
	 */
	public void run() throws IOException;
}
