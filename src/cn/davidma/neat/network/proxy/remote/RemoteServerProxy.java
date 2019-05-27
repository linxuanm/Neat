package cn.davidma.neat.network.proxy.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.packet.Packet;

public class RemoteServerProxy implements IRemoteProxy {
	
	private Socket connection;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	public RemoteServerProxy(String host, int port) throws UnknownHostException, IOException {
		this.connection = new Socket(host, port);
		this.inputStream = new DataInputStream(this.connection.getInputStream());
		this.outputStream = new DataOutputStream(this.connection.getOutputStream());
	}
	
	@Override
	public Side getSide() {
		return Side.SERVER;
	}

	@Override
	public void terminate() throws IOException {
		this.inputStream.close();
		this.outputStream.close();
		this.connection.close();
	}

	@Override
	public void send(Packet packet) {
		
	}
}
