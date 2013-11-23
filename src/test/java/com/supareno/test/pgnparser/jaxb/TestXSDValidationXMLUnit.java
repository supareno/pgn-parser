/*
 * TestXSDValidationXMLUnit.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.log4j.Level;
import org.custommonkey.xmlunit.Validator;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.exceptions.ConfigurationException;
import org.junit.After;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.supareno.pgnparser.Writer;
import com.supareno.pgnparser.jaxb.writer.JAXBPGNWriter;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * The {@code TestXSDValidationXMLUnit} is used to test the XSD generated file conformity.
 * @author reno
 * @version 1.0
 *
 */
public class TestXSDValidationXMLUnit extends XMLTestCase {


	private static final String FILENAME = "target/referencegame.xml";
	private Writer writer = null;

	/**
	 * Tests that the file generated is valid according to the XSD schema.
	 */
	@Test
	public void testByXSDXmlGenerated(){
		writeFile();
		Validator v = null;
		try {
			v = new Validator(new FileReader(FILENAME));
			v.useXMLSchema(true);
			v.setJAXP12SchemaSource(new File("src/main/resources/xml/pgngame-2.2.xsd"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertXMLValid(v);
	}

	private void writeFile(){
		writer = new JAXBPGNWriter();
		writer.setLoggerLevel(Level.ALL);
		writer.setFileName(FILENAME);
		writer.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2);

	}

	@After
	public void cleanUpMethod() {
		File file = new File(FILENAME);
		if (file.exists()) {
			file.delete();
		}
	}
}
