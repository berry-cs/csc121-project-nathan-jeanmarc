import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class SSApp extends PApplet {
	private SubwaySurfers w;

	public void settings() {
		this.size(SSConstants.WIDTH, SSConstants.HEIGHT, P3D);
	}

	public void setup() {
		loadImages();
		
		w = new SubwaySurfers();
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

	void loadImages() {
		for (int i = 0; i < SSConstants.playerImgs.length; i++) {
			SSConstants.playerImgs[i] = loadImage("subwaysurfer" + i + ".png");
		}
		
		SSConstants.playerSprite = new Animation(SSConstants.playerImgs, 75, this);
	}

}
