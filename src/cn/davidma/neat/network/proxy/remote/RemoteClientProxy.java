package cn.davidma.neat.network.proxy.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.Socket;

import cn.davidma.neat.network.NetworkManager;
import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.packet.Packet;

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
		/*this.reader.close();
		this.writer.close();
		connection.close();*/
	}

	@Override
	public void send(Packet packet) {
		try {
			this.writer.writeInt(NetworkManager.getPacketId(packet.getClass()));
			packet.dumpToStream(this.writer);
		} catch (IOException e) {
			throw new IOError(e);
		}
	}
	
	@Override
	public Socket getConnection() {
		return this.connection;
	}

	@Override
	public void run() {
		while (NetworkManager.isRunning()) {
			try {
				NetworkManager.handlerPacketFromInputStream(this.reader);
			} catch (IOException e) {
				throw new IOError(e);
			}
		}
	}
}
