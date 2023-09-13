import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class SSApp extends PApplet {
    SubwaySurfers w;
    
    public void settings() {
        this.size(400, 400);
    }
    
    public void setup() {
        w = new SubwaySurfers(200, 0);
    }
    
    public void draw() {
        w = w.update();
        w.draw(this);
    }
    
    public void mousePressed(MouseEvent mev) {
        w = w.mousePressed(mev);
    }
    
    public void keyPressed(KeyEvent kev) {
        // w = w.keyPressed(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Subway Surfers" }, new SSApp());
    }
}
