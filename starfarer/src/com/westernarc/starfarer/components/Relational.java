package com.westernarc.starfarer.components;

import com.westernarc.starfarer.entities.Node;

public class Relational extends Component {
	public static final int WEST = 0;
	public static final int NORTH = 1;
	public static final int EAST = 3;
	
	public Node ndeWest;
	public Node ndeEast;
	public Node ndeNorth;
	public Node ndeParent;
}
