package cn.davidma.neat.layout;

import java.util.List;
import java.util.function.Consumer;

/**
 * This interface indicates that the object contains childrens.
 * 
 * @author David Ma
 */
public interface IParent<T> {
	
	/**
	 * Adds a child to the parent.
	 * 
	 * @param layoutObject The object to be added as a child.
	 */
	public void addChild(T layoutObject);
	
	/**
	 * Gets the children.
	 * 
	 * @return A list of children.
	 */
	public List<T> getChildren();
	
	/**
	 * Invokes the consumer with each children.
	 * 
	 * @param operation The consumer to be invoked on each children.
	 */
	public void mapChildren(Consumer<T> operation);
	
	/**
	 * Counts the children.
	 * 
	 * @return The number of children.
	 */
	public int childrenCount();
	
	/**
	 * Clears all the children.
	 */
	public void clear();
}
