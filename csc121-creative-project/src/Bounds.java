/* represents the bounds of an entity that is represented as a square drawn from its center point */
class Bounds {
	
	float tBound;
	float bBound;
	float rBound;
	float lBound;
	
	
	Bounds(Posn pos, int width, int height) {
		this.tBound = pos.y - height/2;
		this.bBound = pos.y + height/2;
		this.rBound = pos.x + width/2;
		this.lBound = pos.x - width/2;
	}
}
