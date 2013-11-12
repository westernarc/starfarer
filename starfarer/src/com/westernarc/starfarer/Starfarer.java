package com.westernarc.starfarer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.westernarc.starfarer.entities.*;
import com.westernarc.starfarer.systems.GraphicsSys;
import com.westernarc.starfarer.systems.InputSys;
import com.westernarc.starfarer.systems.PhysicsSys;

public class Starfarer implements ApplicationListener {

	public GraphicsSys sysGraphics;
	public PhysicsSys sysPhysics;
	public InputSys sysInput;
	
	public Node player;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		sysGraphics = new GraphicsSys();
		sysGraphics.create(w, h);

		sysPhysics = new PhysicsSys();
		sysPhysics.create();
		
		sysInput = new InputSys();
		sysInput.create(this, Gdx.input);
		
		sysPhysics.createNode("player");
		player = sysPhysics.getNode("player");
		sysPhysics.createNode(40,0);
	}

	@Override
	public void dispose() {
		sysGraphics.dispose();
	}

	@Override
	public void render() {
		sysInput.update();
		sysPhysics.update();
		sysGraphics.update(sysPhysics.world, Gdx.gl, Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
