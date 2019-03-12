package main.java.cn.davidma.neat.layout;

import java.util.List;
import java.util.function.Consumer;

/**
 * A group can allpy certain actions to all of its children.
 * 
 * <p><b>Notes</b></p>
 * 
 * <p>
 * A group is used to bind objects together, which makes
 * applying operations to them easier.
 * </p>
 * 
 * <p>
 * A group can only be applied relative operations;
 * operations that set a field of the objects in a
 * group to a specific value cannot be accomplished.
 * In this case, the {@link #mapChildren(Consumer)}
 * should be used.
 * </p>
 * 
 * <p><b>Tips</b></p>
 * 
 * <ul>
 * <li>Groups can only be used with relative operations (like move everything 10 by pixels).</li>
 * <li>A group can be rotated by rotating each object around itself, or rotate around a given point.</li>
 * <li>A group can be scaled by scaling each object around its center, or scale around a given point.</li>
 * </ul>
 * 
 * @author David Ma
 */
public class Group extends LayoutObject implements IParent {

	private double opacity;
	private List<LayoutObject> children;
	
	@Override
	public void addChild(LayoutObject layoutObject) {
		children.add(layoutObject);
	}

	@Override
	public List<LayoutObject> getChildren() {
		return children;
	}

	@Override
	public void mapChildren(Consumer<LayoutObject> operation) {
		
	}

	@Override
	public int childrenCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
