package cn.davidma.neat.layout;

import cn.davidma.neat.util.StrUtil;

/**
 * A placeholder object used to structure the game scene.
 * 
 * @author David Ma
 */
public abstract class LayoutObject {
	
	/**
	 * The unique ID given to each LayoutObject;
	 * this will be randomly assigned if left empty.
	 */
	private String id;
	
	public String getId() {
		return id;
	}
	
	/**
	 * Returns this for chaining purpose.
	 */
	public LayoutObject setId(String id) {
		if (StrUtil.isEmpty(id)) {
			this.id = id;
		} else {
			throw new UnsupportedOperationException("The ID of a LayoutObject can only be set once.");
		}
		
		return this;
	}
}
