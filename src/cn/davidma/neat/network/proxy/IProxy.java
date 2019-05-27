package cn.davidma.neat.network.proxy;

import java.io.IOException;

import cn.davidma.neat.network.Side;

/**
 * The base of all proxies.
 * 
 * @author David Ma
 */
public interface IProxy {
	
	/**
	 * Gets the side of the proxy.
	 * 
	 * @return The side of the proxy.
	 */
	public Side getSide();
	
	/**
	 * Terminates the connection.
	 */
	public void terminate() throws IOException;
}
