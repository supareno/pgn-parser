/*
 * JsonPGNParser.java
 *
 * Copyright 2008-2018 supareno
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
package org.supareno.pgnparser.json.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.supareno.pgnparser.AbstractPGNParser;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNParserException;
import org.supareno.pgnparser.model.Games;

import java.io.Reader;

/**
 * The JSON parser could parse JSON files with a single game or a game's array.
 * <p>
 * It uses Jackson's {@link ObjectMapper} to parse JSON. The supported JSON represent is:
 *
 * <pre>
 * <code>
 * {
 *   "games": [
 *     {
 *       "event": "chp",
 *       "site": "USA",
 *       "date": "1956.??.??",
 *       "round": "?",
 *       "white": "Byrne, D.",
 *       "black": "Fischer, R.",
 *       "result": "0-1",
 *       "hits": [
 *         {
 *           "content": "Nf3 Nf6",
 *           "number": "1"
 *         },
 *         {
 *           "content": "c4 g6",
 *           "number": "2"
 *         },...
 *       ]
 *     }
 *   ]
 * }
 *
 * </code>
 * </pre>
 * Null or empty values are not written to the JSON if not set in the object.
 * </p>
 *
 * @author supareno
 * @since 2.3
 */
public final class JsonPGNParser extends AbstractPGNParser {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Games parseFile(final Reader reader) {
        try {
            return OBJECT_MAPPER.readValue(reader, Games.class);
        } catch (Exception e) {
            throw new PGNParserException("Error during parsing", e);
        }
    }

    @Override
    public PGNType getExtensionType() {
        return PGNType.JSON;
    }

}
