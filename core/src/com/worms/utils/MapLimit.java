package com.worms.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

// TODO: Auto-generated Javadoc
public class MapLimit {
	
	/**
	 * Instantiates a new map limit.
	 *
	 * @param object the object
	 * @param world the world
	 */
	public MapLimit(MapObject object, World world){
		Shape shape;
		shape = createPolyline((PolylineMapObject) object);
		Body body;
		BodyDef bdef = new BodyDef();
		bdef.type = BodyDef.BodyType.StaticBody;
		body = world.createBody(bdef);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		body.createFixture(fixtureDef).setUserData(this);
		shape.dispose();
	}
	
	/**
	 * Creates the polyline.
	 *
	 * @param polyline the polyline
	 * @return the chain shape
	 */
	private ChainShape createPolyline(PolylineMapObject polyline){
		float [] vertices = polyline.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];
		
		for (int i = 0; i < worldVertices.length ; i++){
			worldVertices[i] = new Vector2(vertices[i * 2 ] / Constants.PPM, vertices [i * 2 + 1] / Constants.PPM);
			
		}
		ChainShape cs = new ChainShape();
		cs.createChain(worldVertices);
		
		return cs;
	}
}
