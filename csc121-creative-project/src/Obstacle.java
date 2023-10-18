import java.util.Objects;

import processing.core.PApplet;

class Obstacle {
	
	Vector pos;
	int width = SSConstants.OBSTACLE_WIDTH;
	int height = 150;
	int depth = 60;
	
	Bounds3D bounds;
	
	float rearZ;				   // z for the rear of the obstacle
	float frontZ;
	
	boolean offScreen = false;
	
	int track;
	
	float gameSpd;
	
	Obstacle(int track) {
		this.track = track;
		pos = new Vector(SSConstants.tracks[track - 1].getX(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z);
		this.bounds = new Bounds3D(pos, width, height, depth);
	}
	
	void update() {
		pos.newZ(pos.getZ() + SSConstants.gameSpd);
		offScreen = pos.getZ() >= SSConstants.DELETE_POINT;
		
		bounds.update(pos);
		rearZ = bounds.backZ;
		frontZ = bounds.frontZ;
	}
	
	void draw(PApplet c) {
		c.pushMatrix();
		c.fill(70);
		c.translate(pos.getX(), pos.getY(), pos.getZ());
		c.box(width, 20, depth); // draws the base
		
		c.pushMatrix();
		c.fill(90, 60, 40);
		c.translate(-80, -75, 0);
		c.box(40, 130, 20); // draws the left post
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(90, 60, 40);
		c.translate(80, -75, 0);
		c.box(40, 130, 20); // draws the right post
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(150);
		c.translate(0, -120, 0);
		c.box(width-20, 60, 45); // draws the white bar
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(90);
		c.strokeWeight(0.05f);
		c.translate(-80, -115, 0);
		c.sphere(30); // draws the left sphere
		c.strokeWeight(1);
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(90);
		c.strokeWeight(0.05f);
		c.translate(80, -115, 0);
		c.sphere(30); // draws the right sphere
		c.strokeWeight(1);
		c.popMatrix();
		
		c.pushMatrix();  // draws the orange bars
		c.fill(190, 100, 0);
		c.translate(-100, -120, 24);
		c.rect(0, 0, 20, 60);
		c.rect(40, 0, 20, 60);
		c.rect(80, 0, 20, 60); 
		c.rect(120, 0, 20, 60);
		c.rect(160, 0, 20, 60);
		c.rect(200, 0, 20, 60);
		c.popMatrix();
		
		c.popMatrix();
	}
	
	Boolean handleCollision(Player ph) {
		return (frontZ >= ph.getPos().getZ() && 
				   rearZ <= ph.getPos().getZ() && 
				   bounds.top <= ph.getBounds().getbBound() &&
				   track == ph.getCurrentTrack());
	}
	
	public int hashCode() {
		return Objects.hash(bounds, depth, frontZ, gameSpd, height, offScreen, pos, rearZ, track, width);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obstacle other = (Obstacle) obj;
		return Objects.equals(bounds, other.bounds) && depth == other.depth
				&& Float.floatToIntBits(frontZ) == Float.floatToIntBits(other.frontZ)
				&& Float.floatToIntBits(gameSpd) == Float.floatToIntBits(other.gameSpd) && height == other.height
				&& offScreen == other.offScreen && Objects.equals(pos, other.pos)
				&& Float.floatToIntBits(rearZ) == Float.floatToIntBits(other.rearZ) && track == other.track
				&& width == other.width;
	}
	
	
	
	

}
