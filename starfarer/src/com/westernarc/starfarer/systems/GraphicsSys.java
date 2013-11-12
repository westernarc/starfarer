package com.westernarc.starfarer.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GraphicsSys {
	public OrthographicCamera cam;
	public SpriteBatch batch;
	
	Box2DDebugRenderer debugRenderer;
	ShapeRenderer sr;
	
	float width, height;
	
	public void create(float width, float height) {
		this.width = width;
		this.height = height;
		cam = new OrthographicCamera(width, height);

		batch = new SpriteBatch();
		
		debugRenderer = new Box2DDebugRenderer();
		sr = new ShapeRenderer();
		sr.translate( width / 2f, height / 2f , 0);
	}
	public void update(World world, GLCommon g, float tpf) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.update(true);
		debugRenderer.render(world, cam.combined);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.end();
		sr.setColor(Color.BLACK);
		sr.begin(ShapeType.Line);
		sr.box(-5, -5, 0, 10, 10, 0);
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		for(Body body : bodies) {
			Vector2 bvec = body.getWorldCenter();
			sr.rotate(0, 0, 1, body.getAngle());
			sr.box(bvec.x - 5, bvec.y - 5, 0, 10, 10, 0);		
		}
		
		sr.end();
	}
	public void dispose() {
		batch.dispose();
	}
}
