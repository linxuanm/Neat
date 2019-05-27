package cn.davidma.neat.network;

import cn.davidma.neat.network.proxy.local.ILocalProxy;

/**
 * This class handles network connections, as well as allowing other computers to connect to this computer.
 * 
 * <p>
 * This class also provides ways to serialize objects and send them to the according server/client.
 * </p>
 * 
 * @author David Ma
 */
public class NetworkManager {
	
	private static ILocalProxy localProxy;
	
	/**
	 * Creates a server that other clients can connect to on this device.
	 * 
	 * @param port The port in which the server hosts.
	 */
	public static void hostServer(int port) {
		if (localProxy != null) {
			throw new IllegalStateException("This device is already a server/client. Cannot host another server.");
		}
	}
	
	public static void connectToServer(String ip, int port) {
		if (localProxy != null) {
			throw new IllegalStateException("This device is already a server/client. Cannot connect to another server.");
		}
	}
}
