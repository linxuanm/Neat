package main.java.cn.davidma.neat.event.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import main.java.cn.davidma.neat.event.events.EnumEvent;
import main.java.cn.davidma.neat.event.events.EventBase;

/**
 * The event bus handles the registry of event
 * handlers as well as invoking them during certain
 * events.
 * 
 * @author David Ma
 */
public final class EventBus {

	/**
	 * A map that maps all event listeners to their respective events.
	 */
	private Map<EnumEvent, List<Consumer<? extends EventBase>>> listeners;
	
	public EventBus() {
		for (EnumEvent enumEvent: EnumEvent.values()) {
			this.listeners.put(enumEvent, new ArrayList<Consumer<? extends EventBase>>());
		}
	}
}
