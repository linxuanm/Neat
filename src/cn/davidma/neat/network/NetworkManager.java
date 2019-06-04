package cn.davidma.neat.network;

import java.io.DataInputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.davidma.neat.network.packet.Packet;
import cn.davidma.neat.network.packet.PacketHandler;
import cn.davidma.neat.network.proxy.local.ILocalProxy;
import cn.davidma.neat.network.proxy.local.LocalClientProxy;
import cn.davidma.neat.network.proxy.local.LocalServerProxy;
import cn.davidma.neat.network.proxy.remote.IRemoteProxy;

/**
 * This class handles network connections, as well as allowing other computers to connect to this computer.
 * 
 * <p>
 * This class also provides ways to serialize objects and send them to the according server/client.
 * </p>
 * 
 * @author David Ma
 */
public class NetworkManager {
	
	private static ILocalProxy localProxy;
	private static AtomicBoolean running;
	
	private static int packetIdCounter;
	private static List<Class<? extends Packet>> packetList;
	private static Map<Class<? extends Packet>, Integer> packetIdMap;
	private static Map<Class<? extends Packet>, PacketHandler<? extends Packet>> packetHandlerMap;
	
	static {
		running = new AtomicBoolean();
		packetList = new ArrayList<Class<? extends Packet>>();
		packetIdMap = new HashMap<Class<? extends Packet>, Integer>();
		packetHandlerMap = new HashMap<Class<? extends Packet>, PacketHandler<? extends Packet>>();
	}
	
	/**
	 * Creates a server that other clients can connect to on this device.
	 * 
	 * @param port The port in which the server hosts.
	 */
	public static void hostServer(int port) {
		if (localProxy != null) {
			throw new IllegalStateException("This device is already a server/client. Cannot host another server.");
		}
		
		try {
			localProxy = new LocalServerProxy(port);
			new Thread(localProxy).start();
		} catch (IOException e) {
			localProxy = null;
			throw new IOError(e);
		}
	}
	
	/**
	 * Connects to a server.
	 * 
	 * @param host The host of the server (e.g. 127.0.0.1).
	 * @param port The port of the server.
	 */
	public static void connectToServer(String host, int port) {
		if (localProxy != null) {
			throw new IllegalStateException("This device is already a server/client. Cannot connect to another server.");
		}
		
		try {
			localProxy = new LocalClientProxy(host, port);
			new Thread(localProxy).start();
		} catch (IOException e) {
			localProxy = null;
			throw new IOError(e);
		}
	}
	
	/**
	 * Sends the packet to the server.
	 * 
	 * @param packet
	 */
	public static void sendToServer(Packet packet) {
		if (localProxy.getSide() == Side.SERVER) {
			throw new IllegalStateException("This device is a server, and cannot send data to itself.");
		}
		
		localProxy.getAllRemoteProxies().get(0).send(packet);
	}
	
	/**
	 * Send the packet to all connected remote clients.
	 * 
	 * @param packet The packet.
	 */
	public static void sendToAllClients(Packet packet) {
		if (localProxy.getSide() != Side.SERVER) {
			throw new IllegalStateException("This device is a client, and cannot send data to a client.");
		}
		
		localProxy.getAllRemoteProxies().forEach((IRemoteProxy remoteProxy) -> {
			remoteProxy.send(packet);
		});
	}
	
	/**
	 * Closes the connection(s) to (all) remote server/clients.
	 */
	public static void terminateConnection() {
		if (localProxy == null) return;
		
		try {
			running.set(false);
			localProxy.terminate();
		} catch (IOException e) {
			throw new IOError(e);
		}
	}
	
	/**
	 * Registers a packet.
	 * 
	 * @param packetClass The class of the packet.
	 * @param packetHandler An instance of the packet handler.
	 */
	public static void registerPacket(Class<? extends Packet> packetClass, PacketHandler<? extends Packet> handler) {
		packetIdMap.put(packetClass, packetIdCounter++);
		packetHandlerMap.put(packetClass, handler);
	}
	
	/**
	 * Whether the server/client is running.
	 * 
	 * @return Running.
	 */
	public static boolean isRunning() {
		return running.get();
	}
	
	/**
	 * Marks the server/client as running.
	 */
	public static void setRunning() {
		running.set(true);
	}
	
	/**
	 * Gets the ID of a packet.
	 * 
	 * @param packetClass The class of the packet.
	 * @return The ID of the packet.
	 */
	public static int getPacketId(Class<? extends Packet> packetClass) {
		return packetIdMap.get(packetClass);
	}
	
	/**
	 * Gets the corresponding packet from an ID.
	 * 
	 * @param id The packet ID.
	 * @return The class of the packet.
	 */
	public static Class<? extends Packet> getPacketClassFromId(int id) {
		 return packetList.get(id);
	}
	
	/**
	 * Gets the corresponding handler of a packet.
	 * 
	 * @param packetClass The class of the packet.
	 * @return The packet handler.
	 */
	public static PacketHandler<? extends Packet> getPacketHandler(Class<? extends Packet> packetClass) {
		return packetHandlerMap.get(packetClass);
	}
	
	/**
	 * Checks an DataInputStream to see if there are any packets avaliable, and handles them.
	 * 
	 * @param reader The DataInputStream to check.
	 * @throws IOException
	 */
	public static void handlerPacketFromInputStream(DataInputStream reader) throws IOException{

		// Reads the first byte and determine the packet ID.
		final int read = reader.read(new byte[1]);
		if (read != -1) {
			
			// Constructs the packet on the receiveing side.
			final int packetId = (read << 24) + (reader.read() << 16) + (reader.read() << 8) + (reader.read() << 0);
			final Class<? extends Packet> packetClass = NetworkManager.getPacketClassFromId(packetId);
			final PacketHandler<? extends Packet> packetHandler = NetworkManager.getPacketHandler(packetClass);
			try {
				Packet packet = packetClass.newInstance();
				packet.readFromStream(reader);
				packetHandler.handleGeneralPacket(packet);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new InstantiationError(
					"The packet type " + packetClass.toString() + " must have a public nullary constructor."
				);
			}
		}
	}
}
