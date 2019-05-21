package cn.davidma.neat.network.proxy;

import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.packet.Packet;

/**
 * This interface marks that a class is a representation of a device based on the type of connection.
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
	 * Sends a packet to the device.
	 * 
	 * @param packet The packet to be sent.
	 */
	public void send(Packet packet);
}
