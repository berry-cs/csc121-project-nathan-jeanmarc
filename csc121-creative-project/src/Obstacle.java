class Obstacle extends Entity {
	
	Vector pos;
	Bounds bounds;
	int width;
	int height;
	
	boolean offScreen = false;
	
	int track;
	
	float gameSpd;
	
	Obstacle(int width, int height, int track, float gameSpd) {
		super(track);
		switch (track) {
		case 1:
			this.pos = new Vector(300, 200);
			break;
		case 2:
			this.pos = new Vector(600, 200);
			break;
		case 3:
			this.pos = new Vector(900, 200);
			break;
		}
		
		this.width = width;
		this.height = height;
		this.bounds = new Bounds(pos, width, height);
		
		this.gameSpd = gameSpd;
	}
	

	
	

}
