package cn.davidma.neat.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import cn.davidma.neat.capability.IParent;
import cn.davidma.neat.capability.IRelative;

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
public class Group implements IParent<IRelative>, IRelative {
	
	private List<IRelative> children;
	
	public Group() {
		children = new ArrayList<IRelative>();
	}
	
	@Override
	public void addChild(IRelative iRelative) {
		children.add(iRelative);
	}

	@Override
	public List<IRelative> getChildren() {
		return children;
	}

	@Override
	public void mapChildren(Consumer<IRelative> operation) {
		this.children.forEach(operation);
	}

	@Override
	public int childrenCount() {
		return this.children.size();
	}

	@Override
	public void clear() {
		this.children.clear();
	}

	@Override
	public void moveX(int xOffset) {
		this.mapChildren((IRelative i) -> i.moveX(xOffset));
	}

	@Override
	public void moveY(int yOffset) {
		this.mapChildren((IRelative i) -> i.moveY(yOffset));
	}

	@Override
	public void rotate(double angle) {
		this.mapChildren((IRelative i) -> i.rotate(angle));
	}

	@Override
	public void rotate(double angle, int x, int y) {
		this.mapChildren((IRelative i) -> i.rotate(angle, x, y));
	}

	@Override
	public void enlarge(double amount) {
		this.mapChildren((IRelative i) -> i.enlarge(amount));
	}

	@Override
	public void enlarge(double amount, int x, int y) {
		this.mapChildren((IRelative i) -> i.rotate(amount, x, y));
	}

	@Override
	public void hide() {
		this.mapChildren((IRelative i) -> i.hide());
	}

	@Override
	public void show() {
		this.mapChildren((IRelative i) -> i.show());
	}

	@Override
	public Iterator<IRelative> iterator() {
		return this.children.iterator();
	}
}
