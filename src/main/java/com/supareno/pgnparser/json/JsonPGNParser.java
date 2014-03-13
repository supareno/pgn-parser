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

import java.io.Reader;


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
		// TODO: must be reimplmented with json-smart JSON objects
		Games games = null;
	
		return games;
	}

	@Override
	public String getExtension () {
		return TYPE.getExtension ();
	}

}
