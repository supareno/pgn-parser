/*
 * TestPGNParser.java
 * 
 * Copyright 2008-2011 supareno
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.supareno.test.pgnparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.PGNParser;
import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.jaxb.Hit;

/**
 * The {@code TestParser} class is used to test the {@code PGNParser} class.
 * @author supareno
 * @version 1.1
 */
public class TestPGNParser {

	// PGNParser instance
	private Parser parser = null;

	@Before
	public void setUpMethod(){
		parser = new PGNParser(Level.ALL);
	}

	/**
	 * Test method for {@link com.supareno.pgnparser.PGNParser#getLoggerLevel()}.<br>
	 * Executes a test on the {@link com.supareno.pgnparser.PGNParser#getLoggerLevel()}
	 * method. It should return Level.ALL.
	 */
	@Test
	public void testGetLoggerLevel(){
		assertEquals("pgn", parser.getExtension());
		assertEquals(Level.ALL, parser.getLoggerLevel());
	}

	/**
	 * Test method for {@link com.supareno.pgnparser.PGNParser#parseFile(String)}.<br>
	 * Executes a parsing test on the {@link JUnitTestConstants#PGN_FILE}. The retreived game should be
	 * equal to {@link JUnitTestConstants#REFERENCE_GAME_2_2}.
	 */
	@Test
	public void testParseFile(){
		Games games=parser.parseFile(JUnitTestConstants.PGN_FILE);
		assertEquals(1, games.getGame().size());
		assertEquals(JUnitTestConstants.REFERENCE_GAME_2_2, games.getGame().get(0));
	}


	/**
	 * Test method for {@link com.supareno.pgnparser.PGNParser#parseFile(String)}.<br>
	 * Executes a parsing test on the {@link JUnitTestConstants#PGN_FILE}. The retreived game should be
	 * equal to {@link JUnitTestConstants#REFERENCE_GAME_2_2}.
	 */
	@Test
	public void testParseFileTestPgn3(){
		Games games=parser.parseFile(JUnitTestConstants.PGN_FILE_3);
		assertEquals(1, games.getGame().size());
		Game g = games.getGame().get(0);
		assertEquals(JUnitTestConstants.REFERENCE_GAME_2_2.getHits(), g.getHits());
		assertEquals("whiteUSCFTest", g.getWhiteUSCF());
		assertEquals("blackUSCFTest", g.getBlackUSCF());
	}
	
	/**
	 * Test method for {@link com.supareno.pgnparser.PGNParser#parseFile(File)}.<br>
	 * The result should be 3.
	 */
	@Test
	public void testParseFileFile(){
		parser.setLoggerConfiguratorFile(JUnitTestConstants.LOG4J_XML);
		Games list2=parser.parseFile(new File(JUnitTestConstants.PGN_FOLDER+"testFile_2.pgn"));
		assertEquals(3, list2.getGame().size());
		/*
		 * First game:
		 * Attributes tested :-)
		 * [Event "Lloyds Bank op"]
		 * [Site "London"]
		 * [Date "1984.??.??"]
		 * ...
		 */
		Game g1 = list2.getGame().get(0);
		assertEquals("Lloyds Bank op", g1.getEvent());
		assertEquals("London", g1.getSite());
		assertEquals("1984.??.??", g1.getDate());
	}

	/**
	 * Test method for {@link com.supareno.pgnparser.PGNParser#parseFolder(java.lang.String)}.
	 * Executes two tests: one on a "fake" folder and another one on the
	 * {@link JUnitTestConstants#PGN_FOLDER}.
	 */
	@Test
	public void testParseFolder(){
		PGNParser p = (PGNParser)parser;
		assertNull(p.parseFolder("fakefolder"));
		java.util.List<Games> gamesList = p.parseFolder(JUnitTestConstants.PGN_FOLDER);
		assertNotNull(gamesList);
		assertTrue(gamesList.size() > 0);
	}

	@Test
	public void testDrawnGameFisherSpassky() {
		// fisher_spassky.pgn
		final String fileToTest = "fisher_spassky.pgn";
		parser.setLoggerConfiguratorFile(JUnitTestConstants.LOG4J_XML);
		Games list = parser.parseFile(new File(JUnitTestConstants.PGN_TEST_FOLDER + fileToTest));
		assertEquals(1, list.getGame().size());
		/*
		 * [Event "F/S Return Match"]
		 * [Site "Belgrade, Serbia JUG"]
		 * [Date "1992.11.04"]
		 * [Round "29"]
		 * [White "Fischer, Robert J."]
		 * [Black "Spassky, Boris V."]
		 * [Result "1/2-1/2"]
		 * ...
		 */
		Game g1 = list.getGame().get(0);
		assertEquals("F/S Return Match", g1.getEvent());
		assertEquals("Belgrade, Serbia JUG", g1.getSite());
		assertEquals("1992.11.04", g1.getDate());
		assertEquals("29", g1.getRound());
		assertEquals("1/2-1/2", g1.getResult());
		assertEquals(43, g1.getHits().getHit().size());
		/*
		 * testing each hit...
		 */
		List<String> expectedHits = Arrays.asList("e4 e5","Nf3 Nc6","Bb5 a6","Ba4 Nf6","O-O Be7",
				"Re1 b5","Bb3 d6","c3 O-O","h3 Nb8","d4 Nbd7","c4 c6",
				"cxb5 axb5","Nc3 Bb7","Bg5 b4","Nb1 h6","Bh4 c5",
				"dxe5 Nxe4","Bxe7 Qxe7","exd6 Qf6","Nbd2 Nxd6","Nc4 Nxc4",
				"Bxc4 Nb6","Ne5 Rae8","Bxf7+ Rxf7","Nxf7 Rxe1+","Qxe1 Kxf7",
				"Qe3 Qg5","Qxg5 hxg5","b3 Ke6","a3 Kd6","axb4 cxb4",
				"Ra5 Nd5","f3 Bc8","Kf2 Bf5","Ra7 g6","Ra6+ Kc5",
				"Ke1 Nf4","g3 Nxh3","Kd2 Kb5","Rd6 Kc5","Ra6 Nf2",
				"g4 Bd3","Re6");
		int i = 0;
		for ( Hit hit : g1.getHits().getHit() ) {
			assertTrue(hit.getContent() + " must be equal to " + expectedHits.get(i),
					hit.getContent().equals(expectedHits.get(i)));
			i ++;
		}
	}

	@After
	public void cleanUpMethod(){
		parser = null;
	}
}
