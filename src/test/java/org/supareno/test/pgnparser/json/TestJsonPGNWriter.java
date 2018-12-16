/*
 * TestJsonPGNWriter.java
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
package org.supareno.test.pgnparser.json;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.Parser;
import org.supareno.pgnparser.model.Game;
import org.supareno.pgnparser.model.Games;
import org.supareno.pgnparser.json.parser.JsonPGNParser;
import org.supareno.pgnparser.json.writer.JsonPGNWriter;
import org.supareno.test.pgnparser.JUnitTestConstants;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author supareno
 * @version 2.3.0
 */
public class TestJsonPGNWriter {

    private JsonPGNWriter jsonPGNWriter;

    @Test
    public void it_writes_a_game_as_json() {
        jsonPGNWriter = new JsonPGNWriter();
        jsonPGNWriter.setFileName("target/referencegame.json");
        assertTrue(jsonPGNWriter.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2), "json not written");

        Parser parser = new JsonPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile("target/referencegame.json").getGames().get(0);
        assertTrue(expected.equals(parsed));
    }

    @Test
    public void it_writes_a_games_as_json() {
        jsonPGNWriter = new JsonPGNWriter();
        jsonPGNWriter.setFileName("target/referencegames.json");
        Games games = new Games();
        games.getGames().add(JUnitTestConstants.REFERENCE_GAME_2_2);
        games.getGames().add(JUnitTestConstants.REFERENCE_GAME_2_2);
        assertTrue(jsonPGNWriter.writePGNGames(games), "xml not written");

        Parser parser = new JsonPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile("target/referencegames.json").getGames().get(0);
        assertTrue(expected.equals(parsed));
    }


    @AfterEach
    public void deleteCreatedFile() {
        deleteFile("target/referencegame.json");
        deleteFile("target/referencegames.json");
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
