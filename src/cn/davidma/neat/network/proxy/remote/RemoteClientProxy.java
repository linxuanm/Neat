package cn.davidma.neat.network.proxy.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import cn.davidma.neat.network.NetworkManager;
import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.packet.Packet;
import cn.davidma.neat.network.packet.PacketHandler;

public class RemoteClientProxy implements IRemoteProxy {
	
	private Socket connection;
	private DataInputStream reader;
	private DataOutputStream writer;
	
	public RemoteClientProxy(Socket connection) throws IOException {
		this.connection = connection;
		this.reader = new DataInputStream(this.connection.getInputStream());
		this.writer = new DataOutputStream(this.connection.getOutputStream());
	}
	
	@Override
	public Side getSide() {
		return Side.CLIENT;
	}

	@Override
	public void terminate() throws IOException {
		this.reader.close();
		this.writer.close();
		connection.close();
	}

	@Override
	public void send(Packet packet) {
		packet.dumpToStream(this.writer);
	}

	@Override
	public void run() {
		while (NetworkManager.isRunning()) {
			try {
				
				// Reads the first byte and determine the packet ID.
				this.reader.mark(1);
				final int read = this.reader.read(new byte[1]);
				this.reader.reset();
				if (read != -1) {
					
					// Constructs the packet on the receiveing side.
					final int packetId = this.reader.readInt();
					final Class<? extends Packet> packetClass = NetworkManager.getPacketClassFromId(packetId);
					final PacketHandler<? extends Packet> packetHandler = NetworkManager.getPacketHandler(packetClass);
					try {
						Packet packet = packetClass.newInstance();
						packet.readFromStream(this.reader);
						packetHandler.handleGeneralPacket(packet);
					} catch (InstantiationException | IllegalAccessException e) {
						throw new InstantiationError(
							"The packet type " + packetClass.toString() + " must have a public nullary constructor."
						);
					}
				}
			} catch (IOException e) {
				continue;
			}
		}
	}
}
