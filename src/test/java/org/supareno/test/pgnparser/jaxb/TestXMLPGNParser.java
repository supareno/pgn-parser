/*
 * TestXMLPGNParser.java
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
package org.supareno.test.pgnparser.jaxb;


import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.xml.parser.XMLPGNParser;
import org.supareno.pgnparser.model.Games;
import org.supareno.test.pgnparser.AbstractParserValidator;
import org.supareno.test.pgnparser.JUnitTestConstants;

import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * The {@code TestXMLPGNParser} class is used to test the {@code XMLPGNParser} class.
 *
 * @author supareno
 * @version 1.1
 */
public class TestXMLPGNParser extends AbstractParserValidator {

    private XMLPGNParser jaxbParser = null;

    @Test
    void JAXBPGNParser_extension_returns_XML() {
        jaxbParser = new XMLPGNParser();
        assertThat(jaxbParser.getExtensionType()).isEqualTo(PGNType.XML);
    }

    @Test
    void JAXBPGNParser_parsing_null_returns_IllegalArgumentException() {
        jaxbParser = new XMLPGNParser();
        Reader reader = null;
        assertThatThrownBy(() -> jaxbParser.parseFile(reader)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testParseAndEquality() {
        jaxbParser = new XMLPGNParser();
        Games games = jaxbParser.parseFile(JUnitTestConstants.XML_PGN_FILE);
        validateGames(games);
    }

}
