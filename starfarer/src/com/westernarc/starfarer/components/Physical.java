package com.westernarc.starfarer.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Physical extends Component {
	public Body body;
	public BodyDef bodyDef;
	public PolygonShape shape;
	public FixtureDef fixtureDef;
}
