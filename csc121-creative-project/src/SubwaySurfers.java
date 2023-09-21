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
	
	Ground g;
	
	ArrayList<Train> trains;
	
	float gameSpd = SSConstants.gameSpd;  // controls the speed of the game

	/* 
	 * Create new game with player at given x, y and train on the left track
	 */
    public SubwaySurfers() {
    	this.p = new Player();
    	// debugging train
    	this.trains = new ArrayList<Train>();
    	
    	this.g = new Ground();
    }
    
    /*
     * Create new object with given player and train list
     */
    public SubwaySurfers(Player p, ArrayList<Train> t, Ground g) {
    	this.p = p;
    	this.trains = t;
    	this.g = g;
    }
    
    /**
     * Renders a picture of the player and obstacles on the window
     */
    public PApplet draw(PApplet c) {
    	// colors the canvas background
        c.background(45, 160, 230);
        trains.forEach(train -> train.draw(c));
        p.draw(c);
        // positions the camera at (x1,y1,z1) looking toward (x2,y2,z2) SSConstants.HEIGHT/2 + (SSConstants.HEIGHT/2 - p.pos.y)/2
        c.camera(p.pos.x, SSConstants.HEIGHT/2, SSConstants.CAMERA_Z, p.pos.x, SSConstants.HEIGHT, 0, 0, 1, 0);
        g.draw(c);
        return c;
    }

    /**
     * Produces an updated world where the player and obstacles move if needed
     */
    public SubwaySurfers update() {
        p.update();
        trains.removeIf(train -> train.frames.size() == 0);  // removes trains that are off the screen
        trains.forEach(train -> train.update());
        return new SubwaySurfers(p, trains, g);
        
    }
    
    public SubwaySurfers keyPressed(KeyEvent kev) {
    	p.move(kev);
    	
    		if (kev.getKey() == '1') {
    			trains.add( new Train(500, 1, 10, false));
    		} else if (kev.getKey() == '2') {
    			trains.add( new Train(600, 2, 20, false));
    		} else if (kev.getKey() == '3') {
    			trains.add( new Train(700, 3, 25, false));
    		}
    	
    	
    	return new SubwaySurfers(p, trains, g);
    }
    
    /**
     * Produces a string rendering of the position of the
     * drop
     */
    public String toString() {
        return "[" + p.pos.x + ", " + p.pos.y + "]";
    }
}
