package cn.davidma.neat.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.davidma.neat.network.packet.Packet;
import cn.davidma.neat.network.packet.PacketHandler;
import cn.davidma.neat.network.proxy.local.ILocalProxy;

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
	}
	
	/**
	 * Registers a packet.
	 * 
	 * @param packetClass The class of the packet.
	 * @param packetHandler An instance of the packet handler.
	 */
	public static void registerPacket(Class<? extends Packet> packetClass, PacketHandler<? extends Packet> packetHandler) {
		packetIdMap.put(packetClass, packetIdCounter++);
		packetHandlerMap.put(packetClass, packetHandler);
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
}
