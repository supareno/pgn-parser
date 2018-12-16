/*
 * TestJsonPGNParser.java
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
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.json.parser.JsonPGNParser;
import org.supareno.pgnparser.model.Games;
import org.supareno.test.pgnparser.AbstractParserValidator;
import org.supareno.test.pgnparser.JUnitTestConstants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code TestJsonPGNParser} class is used to test the {@code JsonPGNParser} class.
 *
 * @author supareno
 * @version 2.3.0
 */
public class TestJsonPGNParser extends AbstractParserValidator {

    private JsonPGNParser jsonParser = null;

    @Test
    public void JSONParser_extension_returns_JSON() {
        jsonParser = new JsonPGNParser();
        assertThat(jsonParser.getExtensionType()).isEqualTo(PGNType.JSON);
    }

    @Test
    public void it_parses_JSONFile_with_one_game() {
        jsonParser = new JsonPGNParser();
        Games games = jsonParser.parseFile(JUnitTestConstants.JSON_PGN_FILE);
        validateGames(games);
    }

    @Test
    public void it_parses_JSONFile_with_two_games_as_JSONArray() {
        jsonParser = new JsonPGNParser();
        Games games = jsonParser.parseFile(JUnitTestConstants.JSON_2_GAMES_PGN_FILE);
        assertEquals(2, games.getGames().size());
        validateGames(games);
        assertEquals("Byrne, D.", games.getGames().get(1).getWhite());
        assertEquals("Fischer, R.", games.getGames().get(1).getBlack());
    }
}
