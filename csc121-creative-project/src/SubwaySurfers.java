import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class SubwaySurfers {
	Player ph;

	Environment g;

	ArrayList<Train> trains;

	ArrayList<Obstacle> obstacles;

	/*
	 * Create new game with player at given x, y and train on the left track
	 */
	public SubwaySurfers() {
		this.ph = new Player();
		this.trains = new ArrayList<Train>();
		this.obstacles = new ArrayList<Obstacle>();
		this.g = new Environment();
	}

	/*
	 * Create new object with given player and train list
	 */
	public SubwaySurfers(Player ph, ArrayList<Train> t, Environment g, ArrayList<Obstacle> o) {
		this.ph = ph;
		this.trains = t;
		this.g = g;
		this.obstacles = o;
	}

	/**
	 * Renders a picture of the player and obstacles on the window
	 */
	public PApplet draw(PApplet c) {
		// colors the canvas background
		c.background(45, 160, 230);
		trains.forEach(train -> train.draw(c));
		obstacles.forEach(obstacle -> obstacle.draw(c));
		ph.draw(c);
		// positions the camera at (x1,y1,z1) looking toward (x2,y2,z2)
		// SSConstants.HEIGHT/2 + (SSConstants.HEIGHT/2 - p.pos.y)/2
		c.camera(ph.pos.getX(), SSConstants.HEIGHT / 2 - (SSConstants.floorLvl - ph.bounds.bBound), SSConstants.CAMERA_Z,
				ph.pos.getX(), SSConstants.HEIGHT - (SSConstants.floorLvl - ph.bounds.bBound), 0, 0, 1, 0);
		g.draw(c);

		return c;
	}

	/**
	 * Produces an updated world where the player and obstacles move if needed
	 */
	public SubwaySurfers update() {
		ph.update();

		trains.removeIf(train -> (train.pos.getZ() - train.length) >= SSConstants.DELETE_POINT); // removes trains that are
																							// off the screen
		trains.forEach(train -> train.update());

		obstacles.removeIf(obstacle -> obstacle.offScreen); // removes trains that are off the screen
		obstacles.forEach(obstacle -> obstacle.update());

		if (collision()) {
			System.out.println("bruh!!!");
		}

		return new SubwaySurfers(ph, trains, g, obstacles);

	}

	/**
	 * Handles KeyEvents for the SubwaySurfers game
	 * 
	 * @param kev - the KeyEvent to be processed
	 * @return the updated game
	 */
	public SubwaySurfers keyPressed(KeyEvent kev) {
		ph.move(kev);

		if (kev.getKey() == '1') {
			trains.add(new Train(2000, 1, 10, true));
		} else if (kev.getKey() == '2') {
			trains.add(new Train(600, 2, 10, true));
		} else if (kev.getKey() == '3') {
			trains.add(new Train(700, 3, 25, false));
		} else if (kev.getKey() == '4') {
			obstacles.add(new Obstacle(1));
		} else if (kev.getKey() == '5') {
			obstacles.add(new Obstacle(2));
		} else if (kev.getKey() == '6') {
			obstacles.add(new Obstacle(3));
		}

		return new SubwaySurfers(ph, trains, g, obstacles);
	}

	boolean collision() {
	   for (int t = 0; t < trains.size(); t++) {
		   Train tr = trains.get(t);
		   
		   if (tr.frontZ >= ph.pos.getZ() && tr.rearZ <= ph.pos.getZ() && ph.bounds.bBound > tr.bounds.top && tr.track == ph.currentTrack) {
			   if (!tr.hasRamp) {
				   return true;
			   } else {
				   ph.onTrain = true;
				   return false;
			   }
		   }
	   }
	   
	   for (int o = 0; 0 < obstacles.size(); o++) {
		   Obstacle ob = obstacles.get(o);
		      
		   return (ob.frontZ >= ph.pos.getZ() && 
				   ob.rearZ <= ph.pos.getZ() && 
				   ob.bounds.top <= ph.bounds.bBound &&
				   ob.track == ph.currentTrack);
	   }
	   
	   return false;
   }
}
