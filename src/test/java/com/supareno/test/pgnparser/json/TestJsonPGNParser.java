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
package com.supareno.test.pgnparser.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.json.JsonPGNParser;
import com.supareno.test.pgnparser.JUnitTestConstants;
import com.supareno.test.pgnparser.jaxb.AbstractParserValidator;

/**
 * The {@code TestJsonPGNParser} class is used to test the {@code JsonPGNParser} class.
 * 
 * @author supareno
 * @version 2.3.0
 */
public class TestJsonPGNParser extends AbstractParserValidator {

  private JsonPGNParser jsonParser = null;

  @Test
  public void it_parses_JSONFile_with_one_game () {
    jsonParser = new JsonPGNParser ();
    assertEquals ( "json" , jsonParser.getExtension () );
    Games games = jsonParser.parseFile ( JUnitTestConstants.JSON_PGN_FILE );
    validateGames ( games );
  }

  @Test
  public void it_parses_JSONFile_with_two_games_as_JSONArray () {
    jsonParser = new JsonPGNParser ();
    Games games = jsonParser.parseFile ( JUnitTestConstants.JSON_2_GAMES_PGN_FILE );
    assertEquals ( 2 , games.getGame ().size () );
    validateGames ( games );
    assertEquals ( "Me" , games.getGame ().get ( 1 ).getWhite () );
    assertEquals ( "Myself" , games.getGame ().get ( 1 ).getBlack () );
  }
}
