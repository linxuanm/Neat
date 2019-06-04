package cn.davidma.neat.network.proxy.local;

import java.util.List;

import cn.davidma.neat.network.proxy.IProxy;
import cn.davidma.neat.network.proxy.remote.IRemoteProxy;

/**
 * This interface marks that a class is a representation of this device based on the type of connection.
 * 
 * <p>
 * An instance of this is responsible for the main server/client receiving loop on a thread.
 * </p>
 * 
 * @author David Ma
 */
public interface ILocalProxy extends IProxy {
	
	/**
	 * Gets all remote proxies connected. A LocalClientProxy will return a list with one element (The RemoteServerProxy).
	 * 
	 * @return A list of all connected remote proxies.
	 */
	List<? extends IRemoteProxy> getAllRemoteProxies();
}
