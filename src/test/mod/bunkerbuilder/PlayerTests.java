package mod.bunkerbuilder;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTests {

	/* Testing restricting player movement */
	@Test
	public void coordinateTest() {
		GameLevel test = new GameLevel();
		assertTrue("1650 should be less than the minimum x requirement", test.checkXMin(1650)); //1650 should be less than the minimum x requirement
		assertTrue("1668 should be within minimum x requirement", test.checkXMin(1668) == false); //1668 should be within minimum x requirement
		
		assertTrue("1650 should be within bounds", test.checkXMax(1650) == false); //1650 should be within bounds
		assertTrue("1700 should be outside allowed boundaries", test.checkXMax(1700)); //1700 should be outside allowed boundaries
		
		assertTrue("100 should be less than the minimum z requirement", test.checkZMin(100)); //100 should be less than the minimum z requirement
		assertTrue("120 should be within minimum z requirement", test.checkZMin(120) == false); //120 should be within minimum z requirement
		
		assertTrue("120 should be within minimum z requirement", test.checkZMax(120) == false); 
		assertTrue("200 should be outside allowed boundaries", test.checkZMax(200)); //200 should be outside allowed boundaries
	}

}
