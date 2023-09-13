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

	/* create new Player at given x, y */
    public SubwaySurfers(int x, int y) {
    	this.p = new Player(new Posn(x,y));
    }
    
    /*
     * Create new object with given player
     */
    public SubwaySurfers(Player p) {
    	this.p = p;
    }
    
    /**
     * Renders a picture of the drop on the window
     */
    public PApplet draw(PApplet c) {
        c.background(255);
        return p.render(c);
    }

    /**
     * Produces an updated world where the drop moves
     * down a little bit, if it hasn't hit the bottom
     * of the screen yet.
     */
    public SubwaySurfers update() {
        p.update();
        return new SubwaySurfers(p);
    }
    
    public SubwaySurfers keyPressed(KeyEvent kev) {
    	p.move(kev);
    	return new SubwaySurfers(p);
    }
    
    /**
     * Produces a string rendering of the position of the
     * drop
     */
    public String toString() {
        return "[" + p.pos.x + ", " + p.pos.y + "]";
    }
}
