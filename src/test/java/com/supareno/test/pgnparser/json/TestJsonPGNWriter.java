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
package com.supareno.test.pgnparser.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.supareno.pgnparser.Parser;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.json.JsonPGNParser;
import com.supareno.pgnparser.json.JsonPGNWriter;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * @author supareno
 * 
 */
public class TestJsonPGNWriter {

  private static final String FILENAME = "target/referencegame.json";
  private JsonPGNWriter jsonPGNWriter;

  @Test
  public void it_writes_a_game_as_json () {
    jsonPGNWriter = new JsonPGNWriter ();
    jsonPGNWriter.setFileName ( FILENAME );
    assertTrue ( "xml not written" , jsonPGNWriter.writePGNGame ( JUnitTestConstants.REFERENCE_GAME_2_2 ) );

    Parser parser = new JsonPGNParser ();
    parser.setLoggerConfiguratorFile ( JUnitTestConstants.LOG4J_XML );
    Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
    Game parsed = parser.parseFile ( FILENAME ).getGame ().get ( 0 );
    assertEquals ( true , expected.equals ( parsed ) );
  }

  @Test
  public void it_writes_a_games_as_json () {
    jsonPGNWriter = new JsonPGNWriter ();
    jsonPGNWriter.setFileName ( FILENAME );
    Games games = new Games ();
    games.getGame ().add ( JUnitTestConstants.REFERENCE_GAME_2_2 );
    assertTrue ( "xml not written" , jsonPGNWriter.writePGNGames ( games ) );

    Parser parser = new JsonPGNParser ();
    parser.setLoggerConfiguratorFile ( JUnitTestConstants.LOG4J_XML );
    Game expected = JUnitTestConstants.REFERENCE_GAME_2_2;
    Game parsed = parser.parseFile ( FILENAME ).getGame ().get ( 0 );
    assertEquals ( true , expected.equals ( parsed ) );
  }

}
