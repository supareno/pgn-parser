/**
 * 
 */
package org.supareno.test.pgnparser.jaxb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Hit;
import org.supareno.test.pgnparser.JUnitTestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author reno
 *
 */
public class TestGame {

	private Game game1 = null;
	private Game game2 = null;

	@BeforeEach
	void setUpMethod(){
		game1 = new Game();
		game2 = new Game();
	}

	/**
	 * 
	 */
	@Test
	void testEquality(){
		assertEquals(game1, game2);
		assertEquals(game2, game1);
		assertEquals(game1, game1);
		assertEquals(game2, game2);
	}

	/**
	 * 
	 */
	@Test
	void testEquality02(){
		game1 = JUnitTestConstants.getFilledGame();
		game1.setHits(JUnitTestConstants.getFilledHits());
		game2 = JUnitTestConstants.getFilledGame();
		game2.setHits(JUnitTestConstants.getFilledHits());
		assertEquals(game1, game2);
		assertEquals(game2, game1);
		assertEquals(game1, game1);
		assertEquals(game2, game2);
	}

	/**
	 * 
	 */
	@Test
	void testEqualityWithHits(){
		game1.setEvent("event");
		game1.setHits(JUnitTestConstants.getFilledHits());
		game2.setEvent("event");
		game2.setHits(JUnitTestConstants.getFilledHits());
		assertEquals(game1, game2);
		assertEquals(game2, game1);
		assertEquals(game1, game1);
		assertEquals(game2, game2);
	}

	/**
	 * 
	 */
	@Test
	void testNotSame(){
		game2.getHits().getHit().add(new Hit());
		assertNotSame(game1, game2);
		assertNotSame(game2, game1);
	}

}
