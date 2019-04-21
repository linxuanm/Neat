package cn.davidma.neat.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import cn.davidma.neat.util.StrUtil;

/**
 * A scene holds a collection of LayoutObjects. The scene manages
 * what is shown on the screen.
 * 
 * @author David Ma
 */
public class Scene implements IParent<LayoutObject> {
	
	/**
	 * Stores the LayoutObjects in this scene.
	 */
	private Map<String, LayoutObject> sceneObjs;
	/**
	 * Used for generating unique IDs.
	 */
	private int currId;
	
	public Scene() {
		this.sceneObjs = new HashMap<String, LayoutObject>();
		this.currId = 0;
	}
	
	/**
	 * Removes the object with the given ID.
	 * 
	 * @param id The ID of the object to be removed.
	 */
	public void removeObject(String id) {
		this.sceneObjs.remove(id);
	}
	
	/**
	 * Removes the object from the scene.
	 * 
	 * @param layoutObject The object to be removed.
	 */
	public void removeObject(LayoutObject layoutObject) {
		this.removeObject(layoutObject.getId());
	}
	
	/**
	 * Generates an unique ID.
	 * @return
	 */
	private String genId() {
		return String.format("object_%d", this.currId++);
	}
	
	@Override
	public void addChild(LayoutObject layoutObject) {
		if (StrUtil.isEmpty(layoutObject.getId())) {
			layoutObject.setId(this.genId());
		}
		
		this.sceneObjs.put(layoutObject.getId(), layoutObject);
	}

	@Override
	public List<LayoutObject> getChildren() {
		return new ArrayList<LayoutObject>(this.sceneObjs.values());
	}

	@Override
	public void mapChildren(Consumer<LayoutObject> operation) {
		this.sceneObjs.values().forEach(operation);
	}

	@Override
	public int childrenCount() {
		return this.sceneObjs.size();
	}

	@Override
	public void clear() {
		this.sceneObjs.clear();
	}
}
