/* holds positional data x,y */

class Posn {
	float x;
	float y;
	
	Posn(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/* returns a new posn that is the sum of this and that posn */
	Posn posnSum(Posn that) {
		return new Posn(this.x + that.x, this.y + that.y);
	}
	
	/* returns true if this posn is equal to that posn */
	Boolean equalTo(Posn that) {
		return (this.x == that.x && this.y == that.y);
	}
	
	/* returns this posn with the new x value */
	Posn newX(float i) {
		return new Posn(i, this.y);
	}
	
	/* returns this posn with the new y value */
	Posn newY(float i) {
		return new Posn(this.x, i);
	}
	
	public String toString() {
		return "(" + x + "," + y +")";
	}
	

}
