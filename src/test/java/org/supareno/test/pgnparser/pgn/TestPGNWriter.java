/*
 * TestPGNWriter.java
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
package org.supareno.test.pgnparser.pgn;


import java.io.File;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.pgn.parser.PGNParser;
import org.supareno.pgnparser.pgn.writer.PGNWriter;
import org.supareno.pgnparser.Parser;
import org.supareno.pgnparser.Writer;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;
import org.supareno.test.pgnparser.JUnitTestConstants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test class is used to test the PGNWriter class.
 *
 * @author reno
 * @version 1.0
 */
public class TestPGNWriter {

    private static final String FILENAME = "testfilename.pgn";
    private Writer writer = null;


    @BeforeEach
    void setUpWriter() {
        this.writer = new PGNWriter();
        this.writer.setFileName(FILENAME);
    }

    @Test
    void testWriterInitExtension() {
        Writer w = new PGNWriter();
        assertThat( w.getExtensionType()).isEqualTo(PGNType.PGN);
    }

    @Test
    void testWriterInitFileName() {
        assertThat( writer.getFileName() + "." + writer.getExtensionType().getExtension()).isEqualTo(FILENAME);
    }

    @Test
    void testWriterInitFileName_isEqual_to_full_filename() {
        assertThat( writer.getFileName() + "." + writer.getExtensionType().getExtension()).isEqualTo(writer.getFullFileName());
    }

    @Test
    @DisplayName("Trying to write a png game succeeds")
    void testWritePGNGame() {
        assertTrue(writer.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2));
        Parser parser = new PGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game received = parser.parseFile(FILENAME).getGame().get(0);
        assertEquals(expected.getHits(), received.getHits());
    }

    @Test
    @DisplayName("Trying to write a list of png games succeed")
    void testWritePGNGames() {
        Games games = new Games();
        games.getGame().add(JUnitTestConstants.REFERENCE_GAME_2_2);
        assertTrue(writer.writePGNGames(games));
        Parser parser = new PGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game received = parser.parseFile(FILENAME).getGame().get(0);
        assertEquals(expected.getHits(), received.getHits());
    }

    @Test
    @DisplayName("Trying to write a list of null pgn games throws IllegalArgumentException")
    void testWritePGNGamesNull() {
        assertThatThrownBy(() -> writer.writePGNGames(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Trying to write a null pgn game throws IllegalArgumentException")
    void testWritePGNGameNull() {
        assertThatThrownBy(() -> writer.writePGNGame(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @AfterEach
    void cleanUpMethod() {
        File file = new File(FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
