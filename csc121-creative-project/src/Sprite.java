//import java.util.Objects;
import processing.core.PApplet;
//import processing.core.PImage; 
import gifAnimation.*;

class Sprite {
	Gif img;
	PApplet c;
	
	Sprite(String src, PApplet c) {
		this.c = c;
		this.img = new Gif(c, src);
		img.play();	
		
	}
	
	void render(int x, int y) {
		c.image(img, x, y);
	}
	
}
