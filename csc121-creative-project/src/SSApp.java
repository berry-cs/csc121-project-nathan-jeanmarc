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
		PImage[] playerImgs = new PImage[9];

		for (int i = 0; i < playerImgs.length; i++) {
			playerImgs[i] = loadImage("subwaysurfer" + i + ".png");
		}

		SSConstants.playerSprite = new Animation(playerImgs, 75, this);
	}

}
