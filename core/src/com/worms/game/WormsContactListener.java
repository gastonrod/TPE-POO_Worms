package com.worms.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.worms.projectiles.Projectile;
import com.worms.utils.MapLimit;
import com.worms.utils.Tile;

// TODO: Auto-generated Javadoc
public class WormsContactListener implements ContactListener{
	World world;
	
	/**
	 * Instantiates a new worms contact listener.
	 *
	 * @param world the world
	 */
	public WormsContactListener(World world){
		this.world = world;
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic.gdx.physics.box2d.Contact)
	 */
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa == null || fb == null) return;
		if (fb.getUserData() == null || fa.getUserData() == null) return;
		
		
		if (isProjectilePlayerContact( fa, fb) || isProjectileTileContact( fa, fb)){
			Projectile g;
			if ( fb.getUserData() instanceof Projectile){
				g = (Projectile) fb.getUserData();
			} else {
				g = (Projectile) fa.getUserData();
			}
			if ( !g.getDeletionFlag()){
				g.explode();
			}
		} else if (isMapLimitContact(fa,fb)){
			if ( fa.getUserData() instanceof MapLimit){
				if ( fb.getUserData() instanceof Projectile){
					((Projectile)fb.getUserData()).touchedMapLimit();
					Teams.updateTurn();
				} else if ( fb.getUserData() instanceof Worm){
					if (Teams.getPlayerWhoseTurnItIs().equals((Worm)fb.getUserData()))
						Teams.updateTurn();
					((Worm)fb.getUserData()).dispose(true);
				}
			} else {
				if ( fa.getUserData() instanceof Projectile){
					((Projectile)fa.getUserData()).touchedMapLimit();
					Teams.updateTurn();
				} else if ( fa.getUserData() instanceof Worm){
					if (Teams.getPlayerWhoseTurnItIs().equals((Worm)fa.getUserData()))
						Teams.updateTurn();
					((Worm)fa.getUserData()).dispose(true);
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic.gdx.physics.box2d.Contact)
	 */
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

//	Template
//	private boolean is Contact( Fixture a, Fixture b){
//	return ((a.getUserData() instanceof  && b.getUserData() instanceof ) || (b.getUserData() instanceof  && a.getUserData() instanceof ) );
//}
	
	/**
 * Checks if is map limit contact.
 *
 * @param a the a
 * @param b the b
 * @return true, if is map limit contact
 */
private boolean isMapLimitContact( Fixture a, Fixture b){
	return ((a.getUserData() instanceof MapLimit || b.getUserData() instanceof MapLimit));
}
	
	/**
	 * Checks if is projectile tile contact.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if is projectile tile contact
	 */
	private boolean isProjectileTileContact( Fixture a, Fixture b){
		return ((a.getUserData() instanceof Projectile && b.getUserData() instanceof Tile) || (b.getUserData() instanceof Projectile && a.getUserData() instanceof Tile) );
	}
	
	/**
	 * Checks if is projectile player contact.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if is projectile player contact
	 */
	private boolean isProjectilePlayerContact( Fixture a, Fixture b){
		return ((a.getUserData() instanceof Worm && b.getUserData() instanceof Projectile) || (b.getUserData() instanceof Worm && a.getUserData() instanceof Projectile) );
	}

	
}
