package cn.davidma.neat.network.proxy.remote;

import cn.davidma.neat.network.packet.Packet;
import cn.davidma.neat.network.proxy.IProxy;

/**
 * This interface marks that a class is a representation of a remote device based on the type of connection.
 * 
 * @author David Ma
 */
public interface IRemoteProxy extends IProxy {
	
	/**
	 * Sends a packet to the device.
	 * 
	 * @param packet The packet to be sent.
	 */
	void send(Packet packet);
}
