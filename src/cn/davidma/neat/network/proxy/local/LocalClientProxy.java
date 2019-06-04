package cn.davidma.neat.network.proxy.local;

import java.io.IOException;
import java.net.UnknownHostException;

import cn.davidma.neat.network.NetworkManager;
import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.proxy.remote.RemoteServerProxy;

public class LocalClientProxy implements ILocalProxy {
	
	private RemoteServerProxy serverRepr;
	
	public LocalClientProxy(String host, int port) throws UnknownHostException, IOException {
		this.serverRepr = new RemoteServerProxy(host, port);
	}
	
	@Override
	public Side getSide() {
		return Side.CLIENT;
	}

	@Override
	public void terminate() throws IOException {
		this.serverRepr.terminate();
	}

	@Override
	public void run() {
		if (NetworkManager.isRunning()) return;
		NetworkManager.setRunning();
		
		final Thread remoteServerThread = new Thread(this.serverRepr);
		remoteServerThread.start();
	}
}
