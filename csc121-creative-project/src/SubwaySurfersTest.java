import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import processing.event.*;

class SubwaySurfersTest {
	
	// TrackBoards Tests
	
	TrackBoards tb1 = new TrackBoards(SSConstants.BOARD_INITIAL_Z);
	TrackBoards tb2 = new TrackBoards(SSConstants.DELETE_POINT-SSConstants.gameSpd);
	
	@Test // tests every testable method
	void testTrackBoards() {
		tb1.update();
		assertEquals(SSConstants.BOARD_INITIAL_Z+SSConstants.gameSpd, tb1.pos.z);
		assertFalse(tb1.offScreen);

		assertFalse(tb2.offScreen);
		assertEquals(SSConstants.DELETE_POINT-SSConstants.gameSpd, tb2.pos.z);
		tb2.update(); // pushes the board past the delete point
		assertTrue(tb2.offScreen);
		tb2.reset();
		assertEquals(SSConstants.BOARD_INITIAL_Z, tb2.pos.z);
		
	}
	
	// Train Tests
	
	Train t = new Train(100, 2, 10, false);
	
	@Test
	void testTrainUpdate() {
		assertEquals(SSConstants.TRAIN_INITIAL_Z-t.length/2 ,t.pos.z);
		t.update();
		assertEquals(SSConstants.TRAIN_INITIAL_Z-t.length/2+t.vel.z, t.pos.z);
	}
	
	// Player Tests
	
	PlayerHitbox p = new PlayerHitbox();
	
	KeyEvent a = new KeyEvent(null, 0, 0, 0, 'a', java.awt.event.KeyEvent.VK_A);
	KeyEvent d = new KeyEvent(null, 0, 0, 0, 'd', java.awt.event.KeyEvent.VK_D);
	KeyEvent w = new KeyEvent(null, 0, 0, 0, 'w', java.awt.event.KeyEvent.VK_W);
	
	@Test
	void testMove() {
		assertEquals(SSConstants.tracks[1].getxPos(), p.pos.x);
		p.move(a);
		p.update();
		assertEquals(SSConstants.tracks[0].getxPos(), p.pos.x);
		p.move(d);
		p.update();
		assertEquals(SSConstants.tracks[1].getxPos(), p.pos.x);
		p.move(d);
		p.update();
		assertEquals(SSConstants.tracks[2].getxPos(), p.pos.x);
		p.move(a);
		p.update();
		assertEquals(SSConstants.tracks[1].getxPos(), p.pos.x);

	}
	
	@Test
	void testGravity() {
		assertEquals(new PlayerHitbox().vel, p.vel);
		p.jump();
		assertEquals(new Vector(0, -16, 0), p.vel);
		p.update(); // updates twice to make sure player is "in the air"
		p.update();
		assertEquals(new Vector(0, -16, 0).translate(p.gravity), p.vel);
	}
	
	@Test 
	void testJump() {
		assertEquals(new PlayerHitbox().vel, p.vel);
		p.jump();
		assertEquals(new Vector(0, -16, 0), p.vel);
	}
	
	@Test // since the player has not been moved updating it should be the same as doing nothing to it
	void testPlayerUpdate() {
		p.update();
		assertEquals(new PlayerHitbox(), p);
	}

	
	// Obstacle Tests
	
	Obstacle o1 = new Obstacle(1);
	Obstacle o2 = new Obstacle(2);
	Obstacle o3 = new Obstacle(3);
	
	@Test
	void testObstacleUpdate() {
		assertEquals(new Vector(SSConstants.tracks[0].getxPos(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z), 
				o1.pos);
		o1.update();
		assertEquals(new Vector(SSConstants.tracks[0].getxPos(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z+SSConstants.gameSpd), 
				o1.pos);
		
		assertEquals(new Vector(SSConstants.tracks[1].getxPos(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z), 
				o2.pos);
		o2.update();
		assertEquals(new Vector(SSConstants.tracks[1].getxPos(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z+SSConstants.gameSpd), 
				o2.pos);
		
		assertEquals(new Vector(SSConstants.tracks[2].getxPos(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z), 
				o3.pos);
		o3.update();
		assertEquals(new Vector(SSConstants.tracks[2].getxPos(), SSConstants.ENVIRONMENT_Y-30, SSConstants.TRAIN_INITIAL_Z+SSConstants.gameSpd), 
				o3.pos);
	}
	

	// Bounds3D Tests
	
	Bounds3D b3d1 = new Bounds3D(new Vector(0, 0, 0), 100, 200, 400);
	Bounds3D b3d2 = new Bounds3D(new Vector(-40, 60, 100), 400, 500, 20);
	
	@Test
	void testBounds3DUpdate() {
		assertEquals(new Bounds3D(new Vector(100, 200, 300), 100, 200, 400), b3d1.update(new Vector(100, 200, 300)));
		assertEquals(new Bounds3D(new Vector(10, 300, 0), 400, 500, 20), b3d2.update(new Vector(10, 300, 0)));
		
		assertEquals(new Bounds3D(new Vector(100, 200, 300), 10, 20, 30), b3d1.update(new Vector(100, 200, 300), 10, 20, 30));
		assertEquals(new Bounds3D(new Vector(10, 300, 0), 20, 200, 4000), b3d2.update(new Vector(10, 300, 0), 20, 200, 4000));
	}
	
	// Bounds Tests
	
	Bounds b1 = new Bounds(new Vector(0, 0, 0), 100, 200);
	Bounds b2 = new Bounds(new Vector(-40, 60, 100), 400, 500);
	
	@Test
	void testBoundsUpdate() {
		assertEquals(new Bounds(new Vector(100, 200, 300), 100, 200), b1.update(new Vector(100, 200, 300)));
		assertEquals(new Bounds(new Vector(10, 300, 0), 400, 500), b2.update(new Vector(10, 300, 0)));
		
		assertEquals(new Bounds(new Vector(100, 200, 300), 150, 400), b1.update(new Vector(100, 200, 300), 150, 400));
		assertEquals(new Bounds(new Vector(100, 200, 300), 10, 20), b2.update(new Vector(100, 200, 300), 10, 20));
	}
	
	// Vector Tests

	Vector v1 = new Vector(10, 20 ,40);
	Vector v2 = new Vector(-30, 40 ,1000);
	Vector v3 = new Vector(100, -20 ,-400);
	Vector v4 = new Vector(10, 20 ,40);
	

	@Test
	void testTranslate() {
		assertEquals(new Vector(-20, 60, 1040), v1.translate(v2));
		assertEquals(new Vector(70, 20, 600), v2.translate(v3));
		assertEquals(new Vector(110, 0, -360), v1.translate(v3));
	}
	
	@Test
	void testEqualTo() {
		assertTrue(v1.equalTo(v4));
		assertFalse(v1.equalTo(v2));
	}
	
	@Test
	void testNewX() {
		assertEquals(new Vector(100, 20, 40), v1.newX(100));
		assertEquals(new Vector(0, 40, 1000), v2.newX(0));
		assertEquals(new Vector(-40, -20, -400), v3.newX(-40));
	}
	
	@Test
	void testNewY() {
		assertEquals(new Vector(10, 10, 40), v1.newY(10));
		assertEquals(new Vector(-30, 0, 1000), v2.newY(0));
		assertEquals(new Vector(100, -40, -400), v3.newY(-40));
	}
	
	@Test
	void testNewZ() {
		assertEquals(new Vector(10, 20, 10), v1.newZ(10));
		assertEquals(new Vector(-30, 40, 00), v2.newZ(0));
		assertEquals(new Vector(100, -20, -40), v3.newZ(-40));
	}

}
