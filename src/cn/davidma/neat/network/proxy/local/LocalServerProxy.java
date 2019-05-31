package cn.davidma.neat.network.proxy.local;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.proxy.remote.RemoteClientProxy;

public class LocalServerProxy implements ILocalProxy, Iterable<RemoteClientProxy> {
	
	private int port;
	private ServerSocket serverSocket;
	private List<RemoteClientProxy> clients;
	
	public LocalServerProxy(int port) {
		this.port = port;
		this.clients = new ArrayList<RemoteClientProxy>();
	}
	
	@Override
	public Side getSide() {
		return Side.SERVER;
	}

	@Override
	public void terminate() throws IOException {
		this.clients.forEach(client -> client.terminate());
		this.serverSocket.close();
	}

	@Override
	public void run() throws IOException {
		this.serverSocket = new ServerSocket(this.port);
	}
	
	@Override
	public Iterator<RemoteClientProxy> iterator() {
		return this.clients.iterator();
	}
}
