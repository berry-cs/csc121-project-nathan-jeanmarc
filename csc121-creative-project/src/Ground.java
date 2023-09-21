import processing.core.PApplet;

class Ground {
	
	int width = 12000;
	int height = 9500;
	
	Vector pos = new Vector(SSConstants.WIDTH/2, SSConstants.HEIGHT/2, 200);
	
	Ground() {
	}
	
	PApplet draw(PApplet c) {
		c.pushMatrix();
		c.rotateX(c.PI/2f);
		c.translate(0, 0, -900);
		c.fill(0, 80, 0);
		c.rectMode(3);       // rectangle is placed with (x,y) at center
		c.rect(pos.x, pos.y, width, height, 25);
		c.popMatrix();
		
		return c;
	}

}
