/*
 * TestXMLPGNWriter.java
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
import org.supareno.pgnparser.model.Game;
import org.supareno.pgnparser.xml.parser.XMLPGNParser;
import org.supareno.pgnparser.xml.writer.XMLPGNWriter;
import org.supareno.pgnparser.model.Games;
import org.supareno.test.pgnparser.JUnitTestConstants;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code TestXMLPGNWriter} class is used to test the {@link XMLPGNWriter}.
 *
 * @author reno
 * @version 1.0
 */
public class TestXMLPGNWriter {

    private static final String FILENAME = "target/referencegame.xml";
    private Writer writer = null;

    @BeforeEach
    void setUpMethod() {
        writer = new XMLPGNWriter();
    }

    /**
     * Tests that the XMLPGNWriter has the correct extension which is xml.
     */
    @Test
    void testWriterInitExtension() {
        Writer w = new XMLPGNWriter();
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
    void testWritePGNGame() {
        writer.setFileName(FILENAME);
        assertTrue(writer.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2), "xml not written");
        /*
         * test if the xml is written correctly
         * the xml written **MUST** be equals to the JUnitTestConstants.REFERENCE_GAME
         */
        Parser parser = new XMLPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile(FILENAME).getGames().get(0);
        assertEquals(true, expected.equals(parsed));
    }


    @Test
    void testWritePGNGames() {
        final Games games = new Games();
        games.getGames().add(JUnitTestConstants.REFERENCE_GAME_2_2);
        games.getGames().add(JUnitTestConstants.REFERENCE_GAME_2_2);
        final String filename = "target/referencegames.xml";
        writer.setFileName(filename);
        assertTrue(writer.writePGNGames(games), "xml not written");
        /*
         * test if the xml is written correctly
         * the xml written **MUST** be equals to the JUnitTestConstants.REFERENCE_GAME
         */
        Parser parser = new XMLPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile(filename).getGames().get(0);
        assertEquals(true, expected.equals(parsed));
    }

    @AfterEach
    public void deleteCreatedFile() {
        deleteFile(FILENAME);
        deleteFile("target/referencegames.xml");
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
