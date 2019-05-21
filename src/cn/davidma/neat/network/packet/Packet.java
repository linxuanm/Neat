package cn.davidma.neat.network.packet;

import java.nio.ByteBuffer;

/**
 * A packet is a structure that contains data. It is send to other devices via the network.
 * 
 * @author David Ma
 */
public interface Packet {
	
	/**
	 * Reads and populates this packet with data from a given {@link java.nio.ByteBuffer}.
	 * 
	 * @param buffer The ByteBuffer from which this packet should construct.
	 */
	public void readFromBytes(ByteBuffer buffer);
	
	/**
	 * Writes the data from this packet to a given {@link java.nio.ByteBuffer}.
	 * 
	 * @param buffer The ByteBuffer to which data from the packet should write.
	 */
	public void dumpToBytes(ByteBuffer buffer);
}
