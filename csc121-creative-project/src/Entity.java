
/**
 * Represents an object other than the player on the screen
 * that can interact with the player in some way
 * 
 * Contains common fields and methods (like collision with the player)
 */
abstract class Entity {
	int track;  // the track this entity is on, one of: 1, 2, 3;
	
	Entity(int track) {
		this.track = track;
	}
}
