package main.java.cn.davidma.neat.event.events;

/**
 * This enum contains all events. This enum
 * is mainly used for internal event handling.
 * 
 * @author David Ma
 */
public enum EnumEvent {

	GameTickEvent(EventBase.class);

	/**
	 * The class of the event (for matching purposes).
	 */
	private Class<? extends EventBase> eventClass;
	
	private EnumEvent(Class<? extends EventBase> eventClass) {
		this.eventClass = eventClass;
	}
	
	/**
	 * Gets the class of the event.
	 * 
	 * @return The class of the event.
	 */
	public Class<? extends EventBase> createEvent() {
		return this.eventClass;
	}
}
