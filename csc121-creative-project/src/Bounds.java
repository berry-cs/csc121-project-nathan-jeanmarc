/* represents the bounds of an entity that is represented as a square drawn from its center point */
class Bounds {
	
	float tBound;
	float bBound;
	float rBound;
	float lBound;
	
	Posn pos;
	int width;
	int height;
	
	
	Bounds(Posn pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		
		this.tBound = pos.y - height/2;
		this.bBound = pos.y + height/2;
		this.rBound = pos.x + width/2;
		this.lBound = pos.x - width/2;
	}
	
	/* produces an updated version of this Bounds */
	Bounds update(Posn p) {
		return new Bounds(p, width, height);
	}
	
	Bounds update(Posn p, int width, int height) {
		return new Bounds(p, width, height);
	}
}
