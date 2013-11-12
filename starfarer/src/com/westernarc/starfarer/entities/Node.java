package com.westernarc.starfarer.entities;

import java.util.HashMap;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.westernarc.starfarer.components.Component;

public class Node extends Entity {	
	public Node() {
		hmpComponents = new HashMap<String, Component>();
	}
}
