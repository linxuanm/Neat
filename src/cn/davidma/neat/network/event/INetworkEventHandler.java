package cn.davidma.neat.network.event;

public interface INetworkEventHandler {
	
	public void onConnect();
	
	public boolean shouldRefuse();
}
