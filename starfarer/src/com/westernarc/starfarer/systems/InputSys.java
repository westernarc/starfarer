package com.westernarc.starfarer.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.westernarc.starfarer.Starfarer;

public class InputSys {
	private Starfarer game;
	private PhysicsSys sysPhysics;
	private GraphicsSys sysGraphics;
	private Input input;
	
	public int touchX, touchY;
	
	public enum state {
		menu, flight, modify
	} 
	public void create(Starfarer game, Input input) {
		this.game = game;
		this.sysPhysics = game.sysPhysics;
		this.sysGraphics = game.sysGraphics;
		this.input = input;
	}
	public void update() {
		if(input.isTouched()) {
			sysPhysics.applyAngularImpulse(game.player, 20);
		}
	}
}
