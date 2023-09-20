import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 * 
 * Test Processing sketch for 3D rendering
 */
public class Play3D extends PApplet {
	
	TrainSprite3D[] a;
	Player p;
    
    public void settings() {
        //this.size(1200, 800, P3D);
        this.size(960, 640, P3D);
    }
    
    public void setup() {
    	a = new TrainSprite3D[] { new TrainSprite3D(new Vector(300, 400), 0),
    								new TrainSprite3D(new Vector(300, 400), 30),
    								new TrainSprite3D(new Vector(300, 400), 60) };
    	p = new Player(new Vector(600, 400));
    }
    
    public void draw() {
    	background(255);
    	camera(width/2, height/2, 700, width/2, height/2 + (height/2 - p.pos.y)/2, 0, 0, 1, 0);
    	for (TrainSprite3D t : a) { t.draw(this); t.update(); }
    	p.draw(this);
        p.update();

    }
    
    public void keyPressed(KeyEvent kev) {
    	p.move(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Play3D" }, new Play3D());
    }
}


