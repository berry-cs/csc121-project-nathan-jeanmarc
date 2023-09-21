import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.KeyEvent;


/**
 * Represents an interactive application where a drop of
 * water falls down from the top of the window. If the 
 * user clicks the mouse, the drop is moved over to the
 * location of the click;
 */
public class SubwaySurfers {
	Player p;
	
	ArrayList<Train> trains;
	
	float gameSpd = SSConstants.gameSpd;  // controls the speed of the game

	/* 
	 * Create new game with player at given x, y and train on the left track
	 */
    public SubwaySurfers(int x, int y, int z) {
    	this.p = new Player(new Vector(x,y,z));
    	// debugging train
    	this.trains = new ArrayList<Train>();
    	//trains.add(new Train(10, 1, 1, gameSpd, false));
    }
    
    /*
     * Create new object with given player and train list
     */
    public SubwaySurfers(Player p, ArrayList<Train> t) {
    	this.p = p;
    	this.trains = t;
    }
    
    /**
     * Renders a picture of the player and obstacles on the window
     */
    public PApplet draw(PApplet c) {
    	// colors the canvas background
        c.background(255);
        // positions the camera at (x1,y1,z1) looking toward (x2,y2,z2) SSConstants.HEIGHT/2 + (SSConstants.HEIGHT/2 - p.pos.y)/2
        c.camera(SSConstants.WIDTH/2, SSConstants.HEIGHT/2, SSConstants.CAMERA_Z, SSConstants.WIDTH/2, SSConstants.HEIGHT, 0, 0, 1, 0);
        trains.forEach(train -> train.draw(c));
        return p.draw(c);
    }

    /**
     * Produces an updated world where the player and obstacles move if needed
     */
    public SubwaySurfers update() {
        p.update();
        trains.removeIf(train -> train.frames.size() == 0);  // removes trains that are off the screen
        trains.forEach(train -> train.update());
        return new SubwaySurfers(p, trains);
        
    }
    
    public SubwaySurfers keyPressed(KeyEvent kev) {
    	p.move(kev);
    	
    		if (kev.getKey() == '1') {
    			trains.add( new Train(50, 1, 10, false));
    		} else if (kev.getKey() == '2') {
    			trains.add( new Train(50, 2, 20, false));
    		} else if (kev.getKey() == '3') {
    			trains.add( new Train(50, 3, 50, false));
    		}
    	
    	
    	return new SubwaySurfers(p, trains);
    }
    
    /**
     * Produces a string rendering of the position of the
     * drop
     */
    public String toString() {
        return "[" + p.pos.x + ", " + p.pos.y + "]";
    }
}
