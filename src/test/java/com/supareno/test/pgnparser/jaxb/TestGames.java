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
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * @author reno
 *
 */
public class TestGames {

	private Games games1 = null;
	private Games games2 = null;

	@Before
	public void setUpMethod(){
		games1 = new Games();
		games1.getGame().add(null);

		games2 = new Games();
		games2.getGame().add(null);
	}

	/**
	 * Tests the equality with a null Game set.
	 */
	@Test
	public void testEqualityWithNullGame(){
		assertEquals(games1, games2);
		assertEquals(games1, games1);
		assertEquals(games2, games1);
		assertEquals(games2, games2);
	}

	/**
	 * Tests the equality with the same Game set (the Game does not contains any Hit).
	 */
	@Test
	public void testEqualityWithSameGame(){
		games1.getGame().add(JUnitTestConstants.getFilledGame());
		games2.getGame().add(JUnitTestConstants.getFilledGame());
		assertEquals(games1, games2);
		assertEquals(games1, games1);
		assertEquals(games2, games1);
		assertEquals(games2, games2);
	}

	/**
	 * Tests the equality with the same Game set (the Game does not contains any Hit).
	 */
	@Test
	public void testGamesNotEqual01(){
		Game game1 = JUnitTestConstants.getFilledGame();
		Game game2 = JUnitTestConstants.getFilledGame();
		game2.setBlack("otherBlackValue");
		games1.getGame().add(game1);
		games2.getGame().add(game2);
		assertNotSame(games1, games2);
		assertNotSame(games2, games1);
	}

	/**
	 * Tests the equality with the same Game set (the Game does not contains any Hit).
	 */
	@Test
	public void testEqualityWithSameGameAndHits(){
		Game game1 = JUnitTestConstants.getFilledGame();
		Game game2 = JUnitTestConstants.getFilledGame();
		game1.setHits(JUnitTestConstants.getFilledHits());
		game2.setHits(JUnitTestConstants.getFilledHits());
		games1.getGame().add(game1);
		games2.getGame().add(game2);
		assertEquals(games1, games2);
		assertEquals(games1, games1);
		assertEquals(games2, games1);
		assertEquals(games2, games2);
	}

	@After
	public void cleanUpMethod(){
		games1 = null;
		games2 = null;
	}

}
