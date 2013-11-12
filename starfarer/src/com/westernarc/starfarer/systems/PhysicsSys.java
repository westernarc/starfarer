package com.westernarc.starfarer.systems;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.westernarc.starfarer.entities.*;
import com.westernarc.starfarer.components.*;

public class PhysicsSys {
	static final float WORLD_TO_BOX = 0.1f;
	static final float BOX_TO_WORLD = 10f;
	
	public World world;
	
	public HashMap<String, Node> nodes;
	
	public Node getNode(String id) {
		return nodes.get(id);
	}
 	public void create() {
		world = new World(new Vector2(0,0), true);
		nodes = new HashMap<String, Node>();
	}
	
	public Body createBody(BodyDef bodyDef) {
		return world.createBody(bodyDef);
	}
	public void update() {
		world.step(1/60f, 6, 2);
	}
	public void attach(Node child, Node parent, int position) {
		((Relational)parent.hmpComponents.get("Relational")).ndeWest = child;
	}
	public Node getChild(Node parent, int position) {
		switch(position) {
			case Relational.WEST:
				return ((Relational)parent.get("Relational")).ndeWest;
			case Relational.NORTH:
				return ((Relational)parent.get("Relational")).ndeNorth;
			case Relational.EAST:
				return ((Relational)parent.get("Relational")).ndeEast;
		}
		return null;
	}
	public Node getParent(Node child) {
		return ((Relational)child.get("Relational")).ndeParent;
	}
	public Node createNode(String id) {
		Node node = createNode();
		node.id = id;
		nodes.put(id, node);
		return node;
	}
	public Node createNode(float posX, float posY) {
		Physical newPhysical = new Physical();
		newPhysical.bodyDef = new BodyDef();
		newPhysical.bodyDef.position.set(new Vector2(posX,posY));
		newPhysical.bodyDef.type = BodyType.DynamicBody;
		newPhysical.body = createBody(newPhysical.bodyDef);
		
		// Create a fixture definition to apply our shape to
		newPhysical.fixtureDef = new FixtureDef();
		newPhysical.fixtureDef.density = 0.5f; 
		newPhysical.fixtureDef.friction = 0.4f;
		newPhysical.fixtureDef.restitution = 0.6f; // Make it bounce a little bit
		
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		newPhysical.fixtureDef.shape = new PolygonShape();
		((PolygonShape)newPhysical.fixtureDef.shape).setAsBox(10,10);
		// Create our fixture and attach it to the body
		newPhysical.body.createFixture(newPhysical.fixtureDef);
		
		Node newNode = new Node();
		
		//Block nodes should have a graphical, physical, and relational
		newNode.set("Physical", newPhysical);
		newNode.set("Relational", new Relational());
		newNode.set("Graphical", new Graphical());
		return newNode;
	}
	public Node createNode() {
		return createNode(0,0);
	}
	
	public void applyLinearImpulse(Node node, float x, float y) {
		getBody(node).applyLinearImpulse(new Vector2(x,y), getCenter(node), true);
	}
	public void applyAngularImpulse(Node node, float impulse) {
		getBody(node).applyAngularImpulse(impulse, true);
	}
	public Body getBody(Node node) {
		return ((Physical)node.get("Physical")).body;
	}
	public Vector2 getCenter(Node node) {
		return getBody(node).getLocalCenter();
	}
}
