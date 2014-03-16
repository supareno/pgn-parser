/*
 * TestPGNWriter.java
 * 
 * Copyright 2008-2014 supareno
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
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.PGNParser;
import com.supareno.pgnparser.PGNWriter;
import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.Writer;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;

/**
 * This test class is used to test the PGNWriter class.
 * @author reno
 * @version 1.0
 */
public class TestPGNWriter {

	private static final String FILENAME = "testfilename.pgn";
	private Writer writer = null;


	@Before
	public void setUpWriter(){
		this.writer = new PGNWriter();
		this.writer.setFileName(FILENAME);
		this.writer.setLoggerConfiguratorFile("src/main/resources/log4j/external_log4j.xml");
	}

	/**
	 * Tests that the XMLPGNWriter has the correct extension which is xml.
	 */
	@Test
	public void testWriterInitExtension(){
		Writer w = new PGNWriter();
		assertEquals("pgn", w.getExtension());
	}

	/**
	 * Tests that the filename initialisation is correct.
	 */
	@Test
	public void testWriterInitFileName(){
		assertEquals(FILENAME , writer.getFileName() + "." + writer.getExtension());
	}

	/**
	 * Tests that a PGNGame is correctly written.
	 */
	@Test
	public void testWritePGNGame(){
		assertTrue(writer.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2));
		Parser parser = new PGNParser();
		Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
		Game received = parser.parseFile(FILENAME).getGame().get(0);
		assertEquals(expected.getHits(), received.getHits());
	}


	/**
	 * Tests that a PGNGame is correctly written embedded in a Games object.
	 */
	@Test
	public void testWritePGNGames(){
		Games games = new Games();
		games.getGame().add(JUnitTestConstants.REFERENCE_GAME_2_2);
		assertTrue(writer.writePGNGames(games));
		Parser parser = new PGNParser();
		Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
		Game received = parser.parseFile(FILENAME).getGame().get(0);
		assertEquals(expected.getHits(), received.getHits());
	}

	/**
	 * Test that trying to write a null PGNGames object throws an {@link IllegalArgumentException}.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testWritePGNGamesNull(){
		writer.writePGNGames(null);
	}
	
	/**
	 * Test that trying to write a null PGNGame object throws an {@link IllegalArgumentException}.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testWritePGNGameNull(){
		writer.writePGNGame(null);
	}
	
	@After
	public void cleanUpMethod(){
		File file = new File(FILENAME);
		if (file.exists()) {
			file.delete();
		}
	}
}
