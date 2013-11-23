/**
 * 
 */
package com.supareno.test.pgnparser.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Hit;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * @author reno
 *
 */
public class TestGame {

	private Game game1 = null;
	private Game game2 = null;

	@Before
	public void setUpMethod(){
		game1 = new Game();
		game2 = new Game();
	}

	/**
	 * 
	 */
	@Test
	public void testEquality(){
		assertEquals(game1, game2);
		assertEquals(game2, game1);
		assertEquals(game1, game1);
		assertEquals(game2, game2);
	}

	/**
	 * 
	 */
	@Test
	public void testEquality02(){
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
	public void testEqualityWithHits(){
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
	public void testNotSame(){
		game2.getHits().getHit().add(new Hit());
		assertNotSame(game1, game2);
		assertNotSame(game2, game1);
	}



	@After
	public void cleanUpMethod(){
		game1 = null;
		game2 = null;
	}
}
