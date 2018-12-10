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


import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.Parser;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;
import org.supareno.pgnparser.json.parser.JsonPGNParser;
import org.supareno.pgnparser.json.writer.JsonPGNWriter;
import org.supareno.test.pgnparser.JUnitTestConstants;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author supareno
 * @version 2.3.0
 */
public class TestJsonPGNWriter {

    private static final String FILENAME = "target/referencegame.json";
    private JsonPGNWriter jsonPGNWriter;

    @Test
    public void it_writes_a_game_as_json() {
        jsonPGNWriter = new JsonPGNWriter();
        jsonPGNWriter.setFileName(FILENAME);
        assertTrue(jsonPGNWriter.writePGNGame(JUnitTestConstants.REFERENCE_GAME_2_2), "xml not written");

        Parser parser = new JsonPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile(FILENAME).getGame().get(0);
        assertTrue(expected.equals(parsed));
    }

    @Test
    public void it_writes_a_games_as_json() {
        jsonPGNWriter = new JsonPGNWriter();
        jsonPGNWriter.setFileName(FILENAME);
        Games games = new Games();
        games.getGame().add(JUnitTestConstants.REFERENCE_GAME_2_2);
        assertTrue(jsonPGNWriter.writePGNGames(games), "xml not written");

        Parser parser = new JsonPGNParser();
        Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
        Game parsed = parser.parseFile(FILENAME).getGame().get(0);
        assertTrue(expected.equals(parsed));
    }

}
