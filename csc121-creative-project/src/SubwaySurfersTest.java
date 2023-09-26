import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SubwaySurfersTest {

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
