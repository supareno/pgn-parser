/*
 * JsonPGNParser.java
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
package com.supareno.pgnparser.json;

import java.io.IOException;
import java.io.Reader;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.supareno.pgnparser.AbstractPGNParser;
import com.supareno.pgnparser.PGNType;
import com.supareno.pgnparser.jaxb.Games;

/**
 * @author reno
 * @since 2.3.0
 */
public class JsonPGNParser extends AbstractPGNParser {

	/** The PGNType of the Parser: set to {@link PGNType#XML}. */
	public static final PGNType TYPE = PGNType.JSON;

	@Override
	public Games parseFile ( Reader reader ) {
		Games games = null;
		// TODO :-)
		JsonFactory factory = new JsonFactory ();
		try {
			JsonParser parser = factory.createJsonParser ( reader );
			while ( !parser.isClosed () ) {
				// get the token
				JsonToken token = parser.nextToken ();
				// if its the last token then we are done
				if ( token == null ) {
					break;
				}
				// we want to look for a field that says dataset
				System.out.println ( token.name () );
				/*
				 * "Event": "chp",
				 * "Site": "USA",
				 * "Date": "1956.??.??",
				 * "Round": "?",
				 * "White": "Byrne, D.",
				 * "Black": "Fischer, R.",
				 * "Result": "0-1",
				 * "hits":
				 */
				if ( JsonToken.FIELD_NAME.equals ( token ) && "hits".equals ( parser.getCurrentName () ) ) {
					// we are entering the datasets now. The first token should be
					// start of array
					token = parser.nextToken ();
					if ( !JsonToken.START_ARRAY.equals ( token ) ) {
						// bail out
						break;
					}
					// each element of the array is an album so the next token
					// should be {
					token = parser.nextToken ();
					if ( !JsonToken.START_OBJECT.equals ( token ) ) {
						break;
					}
					// we are now looking for a field that says "album_title". We
					// continue looking till we find all such fields. This is
					// probably not a best way to parse this json, but this will
					// suffice for this example.
					while ( true ) {
						token = parser.nextToken ();
						if ( token == null ) {
							break;
						}
						if ( JsonToken.FIELD_NAME.equals ( token ) && "hit".equals ( parser.getCurrentName () ) ) {
							token = parser.nextToken ();
							// System.out.println ( parser.getText () );
						}

					}
				}
			}

		} catch ( JsonParseException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace ();
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		return games;
	}

	@Override
	public String getExtension () {
		return TYPE.getExtension ();
	}

}
