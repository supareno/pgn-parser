/*
 * TestJAXBPGNWriter.java
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
package com.supareno.test.pgnparser.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.Writer;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.parser.JAXBPGNParser;
import com.supareno.pgnparser.jaxb.writer.JAXBPGNWriter;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * The {@code TestJAXBPGNWriter} class is used to test the {@link JAXBPGNWriter}.
 * @author reno
 * @version 1.0
 */
public class TestJAXBPGNWriter {

	private static final String FILENAME = "target/referencegame.xml";
	private Writer writer = null;

	@Before
	public void setUpMethod(){
		writer = new JAXBPGNWriter();
		writer.setLoggerLevel(Level.ALL);
	}

	/**
	 * Tests that the XMLPGNWriter has the correct extension which is xml.
	 */
	@Test
	public void testWriterInitExtension(){
		Writer w = new JAXBPGNWriter();
		assertEquals("The file exyension must be XML", "xml", w.getExtension());
	}

	/**
	 * Executes a test to see if the file name change correctly
	 */
	@Test
	public void testChangeFileName(){
		writer.setFileName("test/newfilename.xml");
		assertEquals("File name must be [test/newfilename.xml] ",
				"test/newfilename.xml", writer.getFileName()+"."+writer.getExtension());
	}

	/** 
	 * Test that trying to write a null PGNGame object throws an {@link IllegalArgumentException}.
	 */
	@Test( expected = IllegalArgumentException.class)
	public void testWritePGNGameNull(){
		writer.writePGNGame(null);
	}
	
	/** 
	 * Test that trying to write a null PGNGames object throws an {@link IllegalArgumentException}.
	 */
	@Test( expected = IllegalArgumentException.class)
	public void testWritePGNGamesNull(){
		writer.writePGNGames(null);
	}
	
	/**
	 * Test method for {@link com.supareno.pgnparser.xml.XMLPGNWriter#writePGNGame(com.supareno.pgnparser.PGNGame)}
	 * Executes a test to see if the file written on the disk return the same game
	 * than the JUnitTestConstants#REFERENCE_GAME
	 */
	@Test
	public void testWritePGNGames(){
		String filename="target/referencegame.xml";
		writer.setFileName(filename);
		assertTrue("xml not written", writer.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2));
		/*
		 * test if the xml is written correctly
		 * the xml written **MUST** be equals to the JUnitTestConstants.REFERENCE_GAME
		 */
		Parser parser = new JAXBPGNParser();
		parser.setLoggerConfiguratorFile(JUnitTestConstants.LOG4J_XML);
		Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
		Game parsed = parser.parseFile(filename).getGame().get(0);
		assertEquals(true, expected.equals(parsed));
	}

	@After
	public void cleanUpMethod(){
		writer = null;
	}


	@AfterClass
	public static void deleteCreatedFile() {
		File file = new File(FILENAME);
		if (file.exists()) {
			file.delete();
		}
	}
}
