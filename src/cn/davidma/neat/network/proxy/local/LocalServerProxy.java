package cn.davidma.neat.network.proxy.local;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.davidma.neat.network.NetworkManager;
import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.proxy.remote.RemoteClientProxy;

public class LocalServerProxy implements ILocalProxy, Iterable<RemoteClientProxy> {
	
	private int port;
	private ServerSocket serverSocket;
	private List<RemoteClientProxy> clients;
	
	public LocalServerProxy(int port) throws IOException {
		this.port = port;
		this.clients = new ArrayList<RemoteClientProxy>();
		this.serverSocket = new ServerSocket(this.port);
	}
	
	@Override
	public Side getSide() {
		return Side.SERVER;
	}

	@Override
	public void terminate() throws IOException {
		for (RemoteClientProxy i: this.clients) {
			i.terminate();
		}
		this.serverSocket.close();
	}

	@Override
	public void run() {
		if (NetworkManager.isRunning()) return;
		NetworkManager.setRunning();
		
		// The main loop of accepting client connections.
		while (NetworkManager.isRunning()) {
			
			try (final Socket connection = this.serverSocket.accept()) {
				
				// Each RemoteClientProxy is instantiated from a connection.
				final RemoteClientProxy remoteClient = new RemoteClientProxy(connection);
				this.clients.add(remoteClient);
				final Thread remoteClientThread = new Thread(remoteClient);
				remoteClientThread.start();
				
			} catch (IOException e) {
				
				// Eats the error.
				continue;
			}
		}
	}
	
	@Override
	public Iterator<RemoteClientProxy> iterator() {
		return this.clients.iterator();
	}
}
