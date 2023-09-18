class Obstacle extends Entity {
	
	Posn pos;
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
			this.pos = new Posn(300, 200);
			break;
		case 2:
			this.pos = new Posn(600, 200);
			break;
		case 3:
			this.pos = new Posn(900, 200);
			break;
		}
		
		this.width = width;
		this.height = height;
		this.bounds = new Bounds(pos, width, height);
		
		this.gameSpd = gameSpd;
	}
	

	
	

}
