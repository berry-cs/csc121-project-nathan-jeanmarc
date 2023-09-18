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
	
	ArrayList<Train> trains;  // should be an array list, just one for now
	
	float gameSpd = 2;  // controls the speed of the game

	/* 
	 * Create new game with player at given x, y and train on the left track
	 */
    public SubwaySurfers(int x, int y) {
    	this.p = new Player(new Posn(x,y));
    	// debugging train
    	this.trains = new ArrayList<Train>();
    	//trains.add(new Train(10, 1, 1, gameSpd, false));
    }
    
    /*
     * Create new object with given player
     */
    public SubwaySurfers(Player p, ArrayList<Train> t) {
    	this.p = p;
    	this.trains = t;
    }
    
    /**
     * Renders a picture of the player and obstacles on the window
     */
    public PApplet draw(PApplet c) {
        c.background(255);
        trains.forEach(train -> train.draw(c));
        return p.draw(c);
    }

    /**
     * Produces an updated world where the drop moves
     * player moves if needed
     */
    public SubwaySurfers update() {
        p.update();
        trains.forEach(train -> train.update());
        return new SubwaySurfers(p, trains);
        
    }
    
    public SubwaySurfers keyPressed(KeyEvent kev) {
    	p.move(kev);
    	
    		if (kev.getKey() == '1') {
    			trains.add( new Train(50, 1, 2, gameSpd, false));
    		} else if (kev.getKey() == '2') {
    			trains.add( new Train(50, 2, 2, gameSpd, false));
    		} else if (kev.getKey() == '3') {
    			trains.add( new Train(50, 3, 2, gameSpd, false));
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
