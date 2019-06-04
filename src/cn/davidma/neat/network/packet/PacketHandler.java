package cn.davidma.neat.network.packet;

/**
 * An instance of this class is responsible of handling a packet and carrying out actions corresponding to the packet.
 * 
 * @param T The type of packet this handler should handle.
 * 
 * @author David Ma
 */
public interface PacketHandler<T extends Packet> {
	
	/**
	 * Handles the packet.
	 * 
	 * @param packet The packet.
	 */
	void handlePacket(T packet);
	
	@SuppressWarnings("unchecked")
	default void handleGeneralPacket(Packet packet) {
		this.handlePacket((T) packet);
	}
}
