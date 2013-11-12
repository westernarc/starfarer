package com.westernarc.starfarer.entities;

import java.util.HashMap;

import com.westernarc.starfarer.components.Component;

public class Entity {
	public String id;
	//Common components:
	//Graphical.  Governs graphical representation of the entity
	//Physical.  Physics controlled body of the entity
	//Relational.  Links to other entities, such as those that are attached.
	public HashMap<String,Component> hmpComponents;
	
	//Retrieves component based on key
	public Component get(String key) {
		return hmpComponents.get(key);
	}
	public void set(String key, Component comp) {
		hmpComponents.put(key, comp);
	}
}
