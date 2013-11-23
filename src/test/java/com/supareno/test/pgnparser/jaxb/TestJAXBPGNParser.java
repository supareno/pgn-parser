/*
 * TestJAXBPGNParser.java
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
package com.supareno.test.pgnparser.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.jaxb.parser.JAXBPGNParser;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * The {@code TestJAXBPGNParser} class is used to test the {@code JAXBPGNParser} class.
 * @author supareno
 * @version 1.1
 */
public class TestJAXBPGNParser {

	private Parser jaxbParser = null;

	@Before
	public void setUpMethod(){
		jaxbParser = new JAXBPGNParser();
		jaxbParser.setLoggerLevel(Level.DEBUG);
	}

	/**
	 * Tests the parser initialisation.
	 */
	@Test
	public void testParserInitialisation(){
		assertEquals("xml", jaxbParser.getExtension());
		assertEquals(Level.DEBUG, jaxbParser.getLoggerLevel());
	}

	/**
	 * Test method for {@link com.supareno.pgnparser.xml.XMLPGNParser#parseXMLFile(String)
	 * Executes a parse on the JUnitTestConstants#XML_PGN_FILE
	 */
	@Test
	public void testParseXMLFileString() {
		Games games = jaxbParser.parseFile(JUnitTestConstants.XML_PGN_FILE);
		assertEquals(1, games.getGame().size());
	}

	/**
	 * Tests that the xml file parsed is equal to the reference game.
	 */
	@Test
	public void testParseAndEquality(){
		Games games = jaxbParser.parseFile(JUnitTestConstants.XML_PGN_FILE);
		if (games == null || games.getGame() == null || games.getGame().size() == 0) {
			fail("games is null or game list is null or empty :-(");
		}
		Game game = games.getGame().get(0);
		assertEquals(JUnitTestConstants.REFERENCE_GAME_2_2, game);
	}



	@After
	public void cleanUpMethod(){
		jaxbParser = null;
	}
}
