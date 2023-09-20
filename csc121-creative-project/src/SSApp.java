import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class SSApp extends PApplet {
    SubwaySurfers w;
    
    public void settings() {
        this.size(1200, 800, P3D);
    }
    
    public void setup() {
        w = new SubwaySurfers(600, 550);
    }
    
    public void draw() {
        w = w.update();
        w.draw(this);
    }
    
    public void keyPressed(KeyEvent kev) {
    	w.keyPressed(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Subway Surfers" }, new SSApp());
    }
}


