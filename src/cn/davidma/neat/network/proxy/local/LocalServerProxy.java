package cn.davidma.neat.network.proxy.local;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.neat.network.Side;
import cn.davidma.neat.network.proxy.remote.RemoteClientProxy;

public class LocalServerProxy implements ILocalProxy {
	
	private List<RemoteClientProxy> clients;
	
	public LocalServerProxy() {
		this.clients = new ArrayList<RemoteClientProxy>();
	}
	
	@Override
	public Side getSide() {
		return Side.SERVER;
	}

	@Override
	public void terminate() {
		
	}

	@Override
	public void run() {
		
	}
}
