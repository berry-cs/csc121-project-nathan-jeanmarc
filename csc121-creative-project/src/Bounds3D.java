import java.util.Objects;

class Bounds3D {
	float frontZ;
	float backZ;
	float top;
	
	Vector pos;
	
	int width;
	int height;
	int depth;
	
	
	Bounds3D(Vector pos, int width, int height, int depth) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		this.frontZ = pos.getZ() + depth/2;
		this.backZ = pos.getZ() - depth/2;
		this.top = SSConstants.floorLvl - height;
	}
	
	/* produces an updated version of this Bounds */
	Bounds3D update(Vector p) {
		return new Bounds3D(p, width, height, depth);
	}
	
	Bounds3D update(Vector p, int width, int height, int depth) {
		return new Bounds3D(p, width, height, depth);
	}

	public int hashCode() {
		return Objects.hash(backZ, depth, frontZ, height, pos, top, width);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bounds3D other = (Bounds3D) obj;
		return Float.floatToIntBits(backZ) == Float.floatToIntBits(other.backZ) && depth == other.depth
				&& Float.floatToIntBits(frontZ) == Float.floatToIntBits(other.frontZ) && height == other.height
				&& Objects.equals(pos, other.pos) && Float.floatToIntBits(top) == Float.floatToIntBits(other.top)
				&& width == other.width;
	}
	
	

}
