package cn.davidma.neat.network.proxy.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.Socket;

import cn.davidma.neat.network.NetworkManager;
import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.packet.Packet;

public class RemoteServerProxy implements IRemoteProxy {
	
	private Socket connection;
	private DataInputStream reader;
	private DataOutputStream writer;
	
	public RemoteServerProxy(String host, int port) throws IOException {
		this.connection = new Socket(host, port);
		this.reader = new DataInputStream(this.connection.getInputStream());
		this.writer = new DataOutputStream(this.connection.getOutputStream());
	}
	
	@Override
	public Side getSide() {
		return Side.SERVER;
	}

	@Override
	public void terminate() throws IOException {
		/*this.reader.close();
		this.writer.close();
		this.connection.close();*/
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
