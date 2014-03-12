/*
 * JAXBPGNParser.java
 * 
 * Copyright 2008-2011 supareno
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.supareno.pgnparser.jaxb.parser;

import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.supareno.pgnparser.AbstractPGNParser;
import com.supareno.pgnparser.PGNType;
import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.jaxb.Games;

/**
 * The {@code JAXBPGNParser} is the JAXB implementation of the {@link Parser} interface.
 * 
 * @author supareno
 * 
 * @version 1.0
 */
public class JAXBPGNParser extends AbstractPGNParser {

	@Override
	public String getExtension () {
		return PGNType.XML.getExtension ();
	}

	@Override
	public Games parseFile ( Reader reader ) {
		JAXBContext jc = null;
		Games games = null;
		try {
			jc = JAXBContext.newInstance ( "com.supareno.pgnparser.jaxb" );
			Unmarshaller u = jc.createUnmarshaller ();
			games = (Games) u.unmarshal ( reader );
		} catch ( JAXBException e ) {
			log ( "error in parseFile(Reader)" , e );
		}
		return games;
	}

}
