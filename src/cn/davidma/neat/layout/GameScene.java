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
import javafx.scene.Node;

/**
 * A scene holds a collection of SceneObjects. The scene manages
 * what is shown on the screen.
 * 
 * @author David Ma
 */
public class GameScene implements IParent<SceneObject<? extends Node>> {
	
	/**
	 * Stores the SceneObjects in this scene.
	 */
	private Map<String, SceneObject<? extends Node>> sceneObjs;
	/**
	 * Used for generating unique IDs.
	 */
	private int currId;
	
	public GameScene() {
		this.sceneObjs = new HashMap<String, SceneObject<? extends Node>>();
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
	public void removeObject(SceneObject<? extends Node> sceneObject) {
		sceneObject.setShouldRemove();
		NeatGame instance = NeatGame.getInstance();
		if (instance.getScene() == this) {
			instance.getGroup().getChildren().remove(sceneObject.getRenderNode());
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
	public void addChild(SceneObject<? extends Node> sceneObject) {
		if (StrUtil.isEmpty(sceneObject.getId())) {
			sceneObject.setId(this.genId());
		}
		
		NeatGame instance = NeatGame.getInstance();
		if (instance.getScene() == this) {
			sceneObject.constructRender();
			instance.getGroup().getChildren().add(sceneObject.getRenderNode());
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
				this.addChild((SceneObject<?>) i);
			}
		}
	}
	
	/**
	 * Finds a SceneObject based on its ID.
	 * 
	 * @param The ID of the SceneObject.
	 * @return The SceneObject.
	 */
	public SceneObject<? extends Node> findChild(String id) {
		return this.sceneObjs.get(id);
	}

	@Override
	public List<SceneObject<? extends Node>> getChildren() {
		return new ArrayList<SceneObject<? extends Node>>(this.sceneObjs.values());
	}

	@Override
	public void mapChildren(Consumer<SceneObject<? extends Node>> operation) {
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
	public Iterator<SceneObject<? extends Node>> iterator() {
		return this.sceneObjs.values().iterator();
	}
	
	/**
	 * Remove objects that are removed as "shouldRemove".
	 */
	public void removeDeleteMark() {
		this.sceneObjs.entrySet().removeIf(entry -> entry.getValue().shouldRemove());
	}
}
