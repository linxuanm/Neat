package cn.davidma.neat.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A packet is a structure that contains data. It is send to other devices via the network.
 * 
 * @author David Ma
 */
public interface Packet {
	
	/**
	 * Reads and populates this packet with data from a given {@link java.io.DataInputStream}.
	 * 
	 * @param buffer The DataInputStream from whose data this packet should construct.
	 */
	public void readFromStream(DataInputStream reader) throws IOException;
	
	/**
	 * Writes the data from this packet to a given {@link java.io.DataOutputStream}.
	 * 
	 * @param buffer The DataOutputStream to which data from the packet should write.
	 */
	public void dumpToStream(DataOutputStream writer) throws IOException;
}
