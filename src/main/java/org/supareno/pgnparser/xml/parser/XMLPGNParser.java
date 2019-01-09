/*
 * XMLPGNParser.java
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
package org.supareno.pgnparser.xml.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.supareno.pgnparser.AbstractPGNParser;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNParserException;
import org.supareno.pgnparser.model.Games;

import java.io.IOException;
import java.io.Reader;

/**
 * The {@code XMLPGNParser} is the XML implementation of the {@link org.supareno.pgnparser.Parser} interface.
 *
 * @author supareno
 * @since 3.0
 */
public final class XMLPGNParser extends AbstractPGNParser {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @Override
    public PGNType getExtensionType() {
        return PGNType.XML;
    }

    @Override
    public Games parseFile(final Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("reader cannot be null");
        }
        try {
            return XML_MAPPER.readValue(reader, Games.class);
        } catch (IOException e) {
            throw new PGNParserException("error during parse", e);
        }
    }

}
