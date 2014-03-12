/*
 * AbstractPGNParser.java
 * 
 * Copyright 2008-2014 supareno
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
package com.supareno.pgnparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import com.supareno.pgnparser.jaxb.Games;

/**
 * The {@code AbstractPGNParser} class is the abstract implementation of the {@link Parser}
 * interface.
 * It defines few methods used by all the parsers.
 * 
 * @author supareno
 * 
 * @version 1.0
 */
public abstract class AbstractPGNParser extends AbstractPGNIO implements Parser {

	@Override
	public Games parseFile ( String file ) {
		return parseFile ( new File ( file ) );
	}

	@Override
	public Games parseFile ( File file ) {
		Reader r = null;
		try {
			r = new FileReader ( file );
		} catch ( FileNotFoundException e ) {
			log ( "error in parseFile(File) " , e );
		}
		return parseFile ( r );
	}

	@Override
	public Games parseURL ( String url ) {
		URL urlTmp = null;
		try {
			urlTmp = new URL ( url );
		} catch ( MalformedURLException e ) {
			log ( "error in parseURL(String) " , e );
		}
		return parseURL ( urlTmp );
	}

	@Override
	public Games parseURL ( URL url ) {
		Games games = null;
		try {
			games = parseFile ( new InputStreamReader ( url.openStream () ) );
		} catch ( IOException e ) {
			log ( "error in parseURL(URL) " , e );
		}
		return games;
	}

}
