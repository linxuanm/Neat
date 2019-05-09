package cn.davidma.neat.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import cn.davidma.neat.capability.IParent;
import cn.davidma.neat.capability.IRelative;
import cn.davidma.neat.object.GameObject;
import cn.davidma.neat.util.StrUtil;

/**
 * A scene holds a collection of LayoutObjects. The scene manages
 * what is shown on the screen.
 * 
 * @author David Ma
 */
public class GameScene implements IParent<GameObject> {
	
	/**
	 * Stores the GameObject in this scene.
	 */
	private Map<String, GameObject> gameObjs;
	/**
	 * Used for generating unique IDs.
	 */
	private int currId;
	
	public GameScene() {
		this.gameObjs = new HashMap<String, GameObject>();
		this.currId = 0;
	}
	
	/**
	 * Removes the object with the given ID.
	 * 
	 * @param id The ID of the object to be removed.
	 */
	public void removeObject(String id) {
		this.gameObjs.remove(id);
	}
	
	/**
	 * Removes the object from the scene.
	 * 
	 * @param layoutObject The object to be removed.
	 */
	public void removeObject(GameObject gameObject) {
		this.removeObject(gameObject.getId());
	}
	
	/**
	 * Generates an unique ID.
	 * @return
	 */
	private String genId() {
		return String.format("object_%d", this.currId++);
	}
	
	@Override
	public void addChild(GameObject gameObject) {
		if (StrUtil.isEmpty(gameObject.getId())) {
			gameObject.setId(this.genId());
		}
		
		this.gameObjs.put(gameObject.getId(), gameObject);
	}
	
	/**
	 * Recursively adds all GameObject in a Group to the scene.
	 * 
	 * @param group The group to be added.
	 */
	public void addGroup(Group group) {
		for (IRelative i: group) {
			if (i instanceof Group) {
				this.addGroup((Group) i);
			}
			if (i instanceof GameObject) {
				this.addChild((GameObject) i);
			}
		}
	}

	@Override
	public List<GameObject> getChildren() {
		return new ArrayList<GameObject>(this.gameObjs.values());
	}

	@Override
	public void mapChildren(Consumer<GameObject> operation) {
		this.gameObjs.values().forEach(operation);
	}

	@Override
	public int childrenCount() {
		return this.gameObjs.size();
	}

	@Override
	public void clear() {
		this.gameObjs.clear();
	}

	@Override
	public Iterator<GameObject> iterator() {
		return this.gameObjs.values().iterator();
	}
}
