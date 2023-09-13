import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class SSApp extends PApplet {
    SubwaySurfers w;
    Player p;
    
    public void settings() {
        this.size(1200, 800);
    }
    
    public void setup() {
        w = new SubwaySurfers(200, 0);
        p = new Player (new Posn(600, 550));
    }
    
    public void draw() {
        w = w.update();
        w.draw(this);
        p.update(this);
    }
    
    public void mousePressed(MouseEvent mev) {
        w = w.mousePressed(mev);
    }
    
    public void keyPressed(KeyEvent kev) {
    	p.move(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Subway Surfers" }, new SSApp());
    }
}
