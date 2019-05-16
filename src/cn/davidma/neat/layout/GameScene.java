package cn.davidma.neat.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import cn.davidma.neat.application.NeatGame;
import cn.davidma.neat.capability.IParent;
import cn.davidma.neat.capability.IRelative;
import cn.davidma.neat.object.SceneObject;
import cn.davidma.neat.util.StrUtil;

/**
 * A scene holds a collection of SceneObjects. The scene manages
 * what is shown on the screen.
 * 
 * @author David Ma
 */
public class GameScene implements IParent<SceneObject> {
	
	/**
	 * Stores the SceneObjects in this scene.
	 */
	private Map<String, SceneObject> sceneObjs;
	/**
	 * Used for generating unique IDs.
	 */
	private int currId;
	
	public GameScene() {
		this.sceneObjs = new HashMap<String, SceneObject>();
		this.currId = 0;
	}
	
	/**
	 * <b>This method should not be used.</b>
	 * Removes the object with the given ID.
	 * 
	 * @param id The ID of the object to be removed.
	 */
	public void removeObject(String id) {
		this.removeObject(this.sceneObjs.get(id));
	}
	
	/**
	 * <b>This method should not be used.</b>
	 * Removes the object from the scene.
	 * 
	 * @param layoutObject The object to be removed.
	 */
	public void removeObject(SceneObject sceneObject) {
		NeatGame instance = NeatGame.getInstance();
		if (instance.getScene() == this) {
			instance.getGroup().getChildren().remove(sceneObject.getRenderNode());
			instance.clickMap.remove(sceneObject.getRenderNode());
		}
	}
	
	/**
	 * Generates an unique ID.
	 * @return
	 */
	private String genId() {
		return String.format("object_%d", this.currId++);
	}
	
	@Override
	public void addChild(SceneObject sceneObject) {
		if (StrUtil.isEmpty(sceneObject.getId())) {
			sceneObject.setId(this.genId());
		}
		
		NeatGame instance = NeatGame.getInstance();
		if (instance.getScene() == this) {
			instance.getGroup().getChildren().add(sceneObject.getRenderNode());
			instance.clickMap.put(sceneObject.getRenderNode(), sceneObject);
		}
		
		this.sceneObjs.put(sceneObject.getId(), sceneObject);
		sceneObject.addToScene(this);
		sceneObject.start();
	}
	
	/**
	 * Recursively adds all SceneObject in a Group to the scene.
	 * 
	 * @param group The group to be added.
	 */
	public void addGroup(Group group) {
		for (IRelative i: group) {
			if (i instanceof Group) {
				this.addGroup((Group) i);
			}
			if (i instanceof SceneObject) {
				this.addChild((SceneObject) i);
			}
		}
	}

	@Override
	public List<SceneObject> getChildren() {
		return new ArrayList<SceneObject>(this.sceneObjs.values());
	}

	@Override
	public void mapChildren(Consumer<SceneObject> operation) {
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

	@Override
	public Iterator<SceneObject> iterator() {
		return this.sceneObjs.values().iterator();
	}
}
