/* holds positional data x,y */

class Vector {
	float x;
	float y;
	float z;
	
	Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/* returns a new posn that is the sum of this and that posn */
	Vector translate(Vector that) {
		return new Vector(this.x + that.x, this.y + that.y, this.z + that.z);
	}
	
	/* returns true if this posn is equal to that posn */
	Boolean equalTo(Vector that) {
		return (this.x == that.x && this.y == that.y && this.z == that.z);
	}
	
	/* returns this posn with the new x value */
	Vector newX(float i) {
		return new Vector(i, this.y, this.z);
	}
	
	/* returns this posn with the new y value */
	Vector newY(float i) {
		return new Vector(this.x, i, this.z);
	}
	
	Vector newZ(float i) {
		return new Vector(this.x, this.y, i);
	}
	
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
	

}
