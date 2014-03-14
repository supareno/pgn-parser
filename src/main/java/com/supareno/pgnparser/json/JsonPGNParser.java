/*
 * JsonPGNParser.java
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
package com.supareno.pgnparser.json;

import java.io.Reader;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.supareno.pgnparser.AbstractPGNParser;
import com.supareno.pgnparser.PGNType;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.jaxb.Hit;
import com.supareno.pgnparser.jaxb.Hits;

/**
 * @author reno
 * @since 2.3.0
 */
public class JsonPGNParser extends AbstractPGNParser {

  private static final String NET_MINIDEV_JSON_JSON_OBJECT_CLASSNAME = "net.minidev.json.JSONObject";
  private static final String NET_MINIDEV_JSON_JSON_ARRAY_CLASSNAME = "net.minidev.json.JSONArray";

  @Override
  public Games parseFile (Reader reader) {
    Games games = null;
    JSONParser parser = new JSONParser(0);
    try {
      Object o = parser.parse(reader);
      games = new Games();
      fillGamesWithJSON(games, o);
    } catch (ParseException e) {
      log("could not parse JSON content", e);
    }
    return games;
  }

  /**
   * 
   * @param games the current Games object
   * @param o the JSON object extract by the parser
   */
  private void fillGamesWithJSON (Games games, Object o) {
    if (o != null) {
      String jsonClass = o.getClass().getName();
      if (jsonClass.equals(NET_MINIDEV_JSON_JSON_OBJECT_CLASSNAME)) {
        Game g = getGameFromJSON((JSONObject) o);
        if (g != null) {
          games.getGame().add(g);
        }
      } else if (jsonClass.equals(NET_MINIDEV_JSON_JSON_ARRAY_CLASSNAME)) {
        JSONArray array = (JSONArray) o;
        for (Object a : array) {
          fillGamesWithJSON(games, a);
        }
      }
    }
  }

  /**
   * @param o the game as JSON object
   * 
   * @return a Game filled with the value of the JSON object of null if
   *         {@code o} is null
   */
  private Game getGameFromJSON (JSONObject o) {
    if (o == null) {
      return null;
    }
    Game game = new Game();
    game.setEvent(getKeyAsStringFromJSON("Event", o));
    game.setSite(getKeyAsStringFromJSON("Site", o));
    game.setDate(getKeyAsStringFromJSON("Date", o));
    game.setRound(getKeyAsStringFromJSON("Round", o));
    game.setWhite(getKeyAsStringFromJSON("White", o));
    game.setBlack(getKeyAsStringFromJSON("Black", o));
    game.setResult(getKeyAsStringFromJSON("Result", o));
    game.setWhiteTitle(getKeyAsStringFromJSON("WhiteTitle", o));
    game.setWhiteElo(getKeyAsStringFromJSON("WhiteElo", o));
    game.setWhiteUSCF(getKeyAsStringFromJSON("WhiteUSCF", o));
    game.setWhiteNA(getKeyAsStringFromJSON("WhiteNA", o));
    game.setWhiteType(getKeyAsStringFromJSON("WhiteType", o));
    game.setBlackTitle(getKeyAsStringFromJSON("BlackTitle", o));
    game.setBlackElo(getKeyAsStringFromJSON("BlackElo", o));
    game.setBlackUSCF(getKeyAsStringFromJSON("BlackUSCF", o));
    game.setBlackNA(getKeyAsStringFromJSON("BlackNA", o));
    game.setBlackType(getKeyAsStringFromJSON("BlackType", o));
    game.setEventDate(getKeyAsStringFromJSON("EventDate", o));
    game.setEventSponsor(getKeyAsStringFromJSON("EventSponsor", o));
    game.setSection(getKeyAsStringFromJSON("Section", o));
    game.setStage(getKeyAsStringFromJSON("Stage", o));
    game.setBoard(getKeyAsStringFromJSON("Board", o));
    game.setOpening(getKeyAsStringFromJSON("Opening", o));
    game.setVariation(getKeyAsStringFromJSON("Variation", o));
    game.setSubVariation(getKeyAsStringFromJSON("SubVariation", o));
    game.setEco(getKeyAsStringFromJSON("Eco", o));
    game.setNic(getKeyAsStringFromJSON("Nic", o));
    game.setTime(getKeyAsStringFromJSON("Time", o));
    game.setUTCTime(getKeyAsStringFromJSON("UTCTime", o));
    game.setUTCDate(getKeyAsStringFromJSON("UTCDate", o));
    game.setTimeControl(getKeyAsStringFromJSON("TimeControl", o));
    game.setSetUp(getKeyAsStringFromJSON("SetUp", o));
    game.setFEN(getKeyAsStringFromJSON("FEN", o));
    game.setTermination(getKeyAsStringFromJSON("Termination", o));
    Hits hits = getHitsFromJSON(o.get("hits"));
    game.setHits(hits);
    return game;
  }

  /**
   * Return a Hits object filled with the hits from {@code object}
   * <p>
   * If {@code object} is null or if it is not a JSONArray, it return null.
   * </p>
   * 
   * @param object the JSON hits array
   * 
   * @return a Hits object filled with the hits from {@code object}
   */
  private Hits getHitsFromJSON (Object object) {
    if (object == null
        || !object.getClass().getName()
            .equals(NET_MINIDEV_JSON_JSON_ARRAY_CLASSNAME)) {
      return null;
    }
    Hits hits = new Hits();
    JSONArray array = (JSONArray) object;
    for (Object a : array) {
      // must be a JSON Object: if not or if null, ignore
      if (a != null
          && a.getClass().getName()
              .equals(NET_MINIDEV_JSON_JSON_OBJECT_CLASSNAME)) {
        JSONObject jsonHit = (JSONObject) a;
        Hit hit = new Hit();
        hit.setNumber(getKeyAsStringFromJSON("number", jsonHit));
        hit.setContent(getKeyAsStringFromJSON("content", jsonHit));
        hits.getHit().add(hit);
      }
    }
    return hits;
  }

  /**
   * @param key the key in the json object
   * @param o the current JSON object
   * 
   * @return the value of {@code key} in {@code o} if not null
   */
  private String getKeyAsStringFromJSON (String key, JSONObject o) {
    return o != null && o.get(key) != null ? (String) o.get(key) : "";
  }

  @Override
  public String getExtension () {
    return PGNType.JSON.getExtension();
  }

}
