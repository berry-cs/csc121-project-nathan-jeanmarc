import processing.core.*;
import processing.event.KeyEvent;

public class SubwaySurfers {
	private Player p;

	private Environment g;
	
	private boolean isGameOver;
		
	/*
	 * Create new game with player at given x, y and train on the left track
	 */
	public SubwaySurfers() {
		this.p = new Player();
		this.g = new Environment();	
	}

	/*
	 * Create new object with given player and train list
	 */
	public SubwaySurfers(Player ph, Environment g, boolean isGameOver) {
		this.p = ph;
		this.g = g;
		this.isGameOver = isGameOver;
	}

	/**
	 * Renders a picture of the player and obstacles on the window
	 */
	public PApplet draw(PApplet c) {
		// colors the canvas background
		c.background(45, 160, 230);
		//c.lights();  // this is where lights functions go, needs tweaking to work. look at documentation
		
		Spawner.getAllObstacles().forEach(ob -> ob.draw(c)); // draws all obstacles
		
		// positions the camera at (x1,y1,z1) looking toward (x2,y2,z2)
		// SSConstants.HEIGHT/2 + (SSConstants.HEIGHT/2 - p.pos.y)/2
		c.camera(p.getPos().getX(), SSConstants.HEIGHT / 2 - (SSConstants.floorLvl - p.getBounds().getbBound()), SSConstants.CAMERA_Z,
				p.getPos().getX(), SSConstants.HEIGHT - (SSConstants.floorLvl - p.getBounds().getbBound()), 0, 0, 1, 0);
		g.draw(c);
		p.draw(c);
		return c;
	}

	/**
	 * Produces an updated world where the player and obstacles move if needed
	 */
	public SubwaySurfers update() {
		if (!isGameOver) {
			p.update();
			
			//Spawner.spawn();
			
			if ( checkCollision() ) {
				System.out.println("bruh!!!");
				gameOver();
			}
	
			Spawner.updateObstacles(); // updates all trains
		}
		
		return new SubwaySurfers(p, g, isGameOver);
	}

	/**
	 * Handles KeyEvents for the SubwaySurfers game
	 * 
	 * @param kev - the KeyEvent to be processed
	 * @return the updated game
	 */
	public SubwaySurfers keyPressed(KeyEvent kev) {
		p.move(kev);

		if (kev.getKey() == '1') {
			Spawner.addTrain(1, true);
		} else if (kev.getKey() == '2') {
			Spawner.addTrain(2, false);
		} else if (kev.getKey() == '3') {
			Spawner.addTrain(3, false);
		} else if (kev.getKey() == '4') {
			Spawner.addBarrier(1);
		} else if (kev.getKey() == '5') {
			Spawner.addBarrier(2);
		} else if (kev.getKey() == '6') {
			Spawner.addBarrier(3);
		}

		return new SubwaySurfers(p, g, isGameOver);
	}

	boolean checkCollision() {
	   for (int t = 0; t < Spawner.getAllObstacles().size(); t++) {
		   IObstacle ob = Spawner.getAllObstacles().get(t);
		   
		   if (ob.handleCollision(p)) return true;
	   }
	   
	   return false;
   }
	
	void gameOver() {
		SSConstants.gameSpd = 0;
		SSConstants.playerSprite.isAnimating = false;
		
		for (int i = 0; i < Spawner.getAllObstacles().size(); i++) {
			IObstacle ob = Spawner.getAllObstacles().get(i);
			if (ob.getType().equals("train")) ((Train) ob).setVel(new Vector(0,0,0));
		}
		
		isGameOver = true;
		System.out.println("the game is over: " + isGameOver);
	}
}
