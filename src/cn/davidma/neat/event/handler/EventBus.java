package cn.davidma.neat.event.handler;

import java.awt.Event;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
	private Map<Class<? extends EventBase>, List<Method>> listeners;
	
	public EventBus() {
		this.listeners = new HashMap<Class<? extends EventBase>, List<Method>>();
	}
	
	/**
	 * Registers an instance of event handler. Scans all methods
	 * annotated with {@link cn.davidma.neat.event.handler.EventHandler}
	 * and add them to the event bus.
	 * 
	 * @param handlerObject An instance of event handler class.
	 */
	public void register(Object handlerObject) {
		Class<?> clazz = handlerObject.getClass();
		
		while (clazz != Object.class) {
			for (Method method: clazz.getDeclaredMethods()) {
				if (method.isAnnotationPresent(EventHandler.class)) {
					Class<?>[] parameterClazz = method.getParameterTypes();
					
					if (parameterClazz.length != 1) {
						// Warning here.
						continue;
					}
					
					Class<?> eventClazz = parameterClazz[0];
					if (EventBase.class.isAssignableFrom(eventClazz)) {
						
					}
				}
			}
			
			clazz = clazz.getSuperclass();
		}
	}
	
	/**
	 * Subscribes the event handler to the given event.
	 */
	private void registerHandlerForEvent(Class<?> clazz, Method method) {
		if (clazz instanceof Class<? extends EventBase>) {
			
		}
	}
}
