/*
 * JAXBPGNParser.java
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
package com.supareno.pgnparser.jaxb.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.supareno.pgnparser.AbstractPGNParser;
import com.supareno.pgnparser.PGNType;
import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.jaxb.Games;

/**
 * The {@code JAXBPGNParser} is the JAXB implementation of the {@link Parser} interface.
 * @author reno
 * @version 1.0
 */
public class JAXBPGNParser extends AbstractPGNParser {

	/** The PGNType of the Parser: set to {@link PGNType#XML}. */
	public static final PGNType TYPE = PGNType.XML;

	/* (non-Javadoc)
	 * @see com.supareno.pgnparser.Parser#getExtension()
	 */
	public String getExtension() { return TYPE.getExtension(); }

	/* (non-Javadoc)
	 * @see com.supareno.pgnparser.Parser#parseFile(java.lang.String)
	 */
	public Games parseFile(String file) {
		return parseFile(new File(file));
	}

	/* (non-Javadoc)
	 * @see com.supareno.pgnparser.Parser#parseFile(java.io.File)
	 */
	public Games parseFile(File file) {
		Reader r = null;
		try {
			r=new FileReader(file);
		} catch (FileNotFoundException e) {
			log("error in parseFile(File) ",e);
		}
		return parseFile(r);
	}

	/* (non-Javadoc)
	 * @see com.supareno.pgnparser.Parser#parseFile(java.io.Reader)
	 */
	public Games parseFile(Reader reader) {
		JAXBContext jc = null;
		Games games = null;
		try {
			jc = JAXBContext.newInstance( "com.supareno.pgnparser.jaxb" );
			Unmarshaller u = jc.createUnmarshaller();
			games = (Games)u.unmarshal(reader);
		} catch (JAXBException e) {
			log("error in parseFile(Reader)", e);
		}
		return games;
	}

	/* (non-Javadoc)
	 * @see com.supareno.pgnparser.Parser#parseURL(java.lang.String)
	 */
	public Games parseURL(String url) {
		URL urlTmp = null;
		try {
			urlTmp = new URL(url);
		} catch (MalformedURLException e) {
			log("error in parseURL(String) ", e);
		}
		return parseURL(urlTmp);
	}

	/* (non-Javadoc)
	 * @see com.supareno.pgnparser.Parser#parseURL(java.net.URL)
	 */
	public Games parseURL(URL url) {
		Games games = null;
		try {
			games = parseFile(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			log("error in parseURL(URL) ", e);
		}
		return games;
	}

}
