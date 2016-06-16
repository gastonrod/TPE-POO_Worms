package com.worms.drawables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class DrawablePlayer {

	private Vector2 pos;
	private Texture tex;
	
	public DrawablePlayer(Vector2 pos, Texture tex){
		this.pos = pos;
		this.tex = tex;
	}
	
	public Vector2 getPos(){
		return pos;
	}
	
	public Texture getTex(){
		return tex;
	}
	
}