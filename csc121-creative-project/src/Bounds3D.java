
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
		
		this.frontZ = pos.z + depth/2;
		this.backZ = pos.z - depth/2;
		this.top = SSConstants.floorLvl - height;
	}
	
	/* produces an updated version of this Bounds */
	Bounds3D update(Vector p) {
		return new Bounds3D(p, width, height, depth);
	}
	
	Bounds3D update(Vector p, int width, int height, int depth) {
		return new Bounds3D(p, width, height, depth);
	}

}
