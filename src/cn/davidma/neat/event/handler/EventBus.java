package cn.davidma.neat.event.handler;

import java.awt.Event;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.davidma.neat.event.events.EventBase;

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
	private Map<Class<?>, List<EventEntry>> listeners;
	
	public EventBus() {
		this.listeners = new HashMap<Class<?>, List<EventEntry>>();
	}
	
	/**
	 * Registers an instance of event handler. Scans all methods
	 * annotated with {@link cn.davidma.neat.event.handler.EventHandler}
	 * and add them to the event bus.
	 * 
	 * @param handlerObject An instance of event handler class.
	 */
	public void register(Object handlerObject) {
		for (Method method: handlerObject.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(EventHandler.class)) {
				Class<?>[] parameterClazz = method.getParameterTypes();
				
				if (parameterClazz.length != 1) {
					// Warning here.
					continue;
				}
				
				Class<?> eventClazz = parameterClazz[0];
				if (EventBase.class.isAssignableFrom(eventClazz)) {
					if (!this.listeners.containsKey(eventClazz)) {
						this.listeners.put(eventClazz, new ArrayList<EventEntry>());
					}
					
					this.listeners.get(eventClazz).add(new EventEntry(handlerObject, method));
				} else {
					// Warning here.
				}
			}
		}
	}
	
	/**
	 * Calls all event handlers for the given event.
	 * 
	 * @param eventClazz The event whose handlers are to be called.
	 */
	public void trigger(Class<?> eventClazz) {
		if (!EventBase.class.isAssignableFrom(eventClazz)) {
			throw new InvalidParameterException("The event to be triggered does not inherit EventBase.");
		}
		
		if (this.listeners.containsKey(eventClazz)) {
			List<EventEntry> eventEntries = this.listeners.get(eventClazz);
			for (EventEntry i: eventEntries) {
				try {
					i.getHandlerMethod().invoke(i.getHandlerInstance());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// Warning here.
				}
			}
		}
	}
	
	/**
	 * A read-only entry that stores a method to its matching object.
	 * 
	 * @author David Ma
	 */
	private class EventEntry {
		
		/**
		 * The object in whose class the handlerMethod exists.
		 */
		private Object eventHandlerObject;
		/**
		 * The method to handle the event.
		 */
		private Method handlerMethod;
		
		/**
		 * Creates an entry for events and match each method to its object.
		 * 
		 * @param eventHandlerObject The object in whose class the handlerMethod exists.
		 * @param handlerMethod The method to handle the event.
		 */
		public EventEntry(Object eventHandlerObject, Method handlerMethod) {
			this.eventHandlerObject = eventHandlerObject;
			this.handlerMethod = handlerMethod;
		}
		
		public Object getHandlerInstance() {
			return this.eventHandlerObject;
		}
		
		public Method getHandlerMethod() {
			return this.handlerMethod;
		}
	}
}
