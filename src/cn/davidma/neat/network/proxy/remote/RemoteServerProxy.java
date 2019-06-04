package cn.davidma.neat.network.proxy.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.davidma.neat.network.NetworkManager;
import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.packet.Packet;

public class RemoteServerProxy implements IRemoteProxy {
	
	private Socket connection;
	private DataInputStream reader;
	private DataOutputStream writer;
	
	public RemoteServerProxy(String host, int port) throws UnknownHostException, IOException {
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
		this.reader.close();
		this.writer.close();
		this.connection.close();
	}

	@Override
	public void send(Packet packet) {
		packet.dumpToStream(this.writer);
	}

	@Override
	public void run() {
		while (NetworkManager.isRunning()) {
			
		}
	}
}
