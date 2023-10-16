/* holds positional data x,y */

import java.util.Objects;

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
		//return new Vector(this.x + that.x, this.y + that.y, this.z + that.z);
		this.x += that.x;
		this.y += that.y;
		this.z += that.z;
		return this;
	}
	
	/* returns true if this posn is equal to that posn */
	Boolean equalTo(Vector that) {
		return (this.x == that.x && this.y == that.y && this.z == that.z);
	}
	
	/* returns this posn with the new x value */
	Vector newX(float i) {
		//return new Vector(i, this.y, this.z);
		this.x = i;
		return this;
	}
	
	/* returns this posn with the new y value */
	Vector newY(float i) {
		//return new Vector(this.x, i, this.z);
		this.y = i;
		return this;
	}
	
	Vector newZ(float i) {
		//return new Vector(this.x, this.y, i);
		this.z = i;
		return this;
	}
	
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		return Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
				&& Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
	}
	

	
}
