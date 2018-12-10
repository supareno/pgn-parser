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
package org.supareno.test.pgnparser.jaxb;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.Parser;
import org.supareno.pgnparser.Writer;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.parser.JAXBPGNParser;
import org.supareno.pgnparser.jaxb.writer.JAXBPGNWriter;
import org.supareno.test.pgnparser.JUnitTestConstants;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code TestJAXBPGNWriter} class is used to test the {@link JAXBPGNWriter}.
 *
 * @author reno
 * @version 1.0
 */
public class TestJAXBPGNWriter {

    private static final String FILENAME = "target/referencegame.xml";
    private Writer writer = null;

    @BeforeEach
    void setUpMethod() {
        writer = new JAXBPGNWriter();
    }

    /**
     * Tests that the XMLPGNWriter has the correct extension which is xml.
     */
    @Test
    void testWriterInitExtension() {
        Writer w = new JAXBPGNWriter();
        assertThat(w.getExtensionType()).isEqualTo(PGNType.XML);
    }

    /**
     * Executes a test to see if the file name change correctly
     */
    @Test
    void testChangeFileName() {
        final String filename="test/newfilename.xml";
        writer.setFileName(filename);
        assertThat(writer.getFileName() + "." + writer.getExtensionType().getExtension()).isEqualTo(filename);
    }

    /**
     * Test that trying to write a null PGNGame object throws an {@link IllegalArgumentException}.
     */
    @Test
    void testWritePGNGameNull() {
        assertThatThrownBy(() -> writer.writePGNGame(null)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test that trying to write a null PGNGames object throws an {@link IllegalArgumentException}.
     */
    @Test
    void testWritePGNGamesNull() {
        assertThatThrownBy(() -> writer.writePGNGames(null)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void testWritePGNGames() {
        final String filename = "target/referencegame.xml";
        writer.setFileName(filename);
        assertTrue(writer.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2), "xml not written");
        /*
         * test if the xml is written correctly
         * the xml written **MUST** be equals to the JUnitTestConstants.REFERENCE_GAME
         */
        Parser parser = new JAXBPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile(filename).getGame().get(0);
        assertEquals(true, expected.equals(parsed));
    }

    @AfterEach
    public void deleteCreatedFile() {
        File file = new File(FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
