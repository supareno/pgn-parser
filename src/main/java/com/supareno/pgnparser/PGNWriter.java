/*
 * PGNWriter.java
 * 
 * Copyright 2008-2011 supareno
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.supareno.pgnparser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.jaxb.Hit;

/**
 * The {@code PGNWriter} class is used to write pgn files from PGNGames.
 * 
 * @author supareno
 * @version 2.0
 */
public class PGNWriter extends AbstractPGNWriter {

  public boolean writePGNGames (Games games) throws IllegalArgumentException {
    if (games == null) {
      throw new IllegalArgumentException("the PGNGame or the file name is null");
    }
    boolean ok = true;
    BufferedWriter out = null;
    try {
      out = new BufferedWriter(new FileWriter(getFullFileName()));
      for (Game game : games.getGame()) {
        if (game == null) {
          continue;
        }
        out.write("\n");
        // writing attributes
        out.write(getAttributesStringRepresentation(game));
        // writing hits & result
        List<Hit> hits = game.getHits().getHit();
        if (hits != null && hits.size() > 0) {
          for (Hit hit : hits) {
            out.write(hit.getNumber() + "." + hit.getContent());
            out.write(" ");
          }
        }
      }
    } catch (Exception e) {
      ok = false;
    } finally {
      try {
        out.close();
      } catch (IOException e) {
        ok = false;
        log("cannot close the writer !");
      }
    }
    return ok;
  }

  public String getExtension () {
    return PGNType.PGN.getExtension();
  }

  /**
   * Returns a String representation of the attrbutes of the current Game
   * object.
   * 
   * @param pGame the current game object.
   */
  private String getAttributesStringRepresentation (final Game pGame) {
    StringBuilder sb = new StringBuilder();
    sb.append("[").append(PGNParserConstants.EVENT_ATTR).append(" \"");
    sb.append(pGame.getEvent()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.SITE_ATTR).append(" \"");
    sb.append(pGame.getSite()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.DATE_ATTR).append(" \"");
    sb.append(pGame.getDate()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.ROUND_ATTR).append(" \"");
    sb.append(pGame.getRound()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.WHITE_ATTR).append(" \"");
    sb.append(pGame.getWhite()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.BLACK_ATTR).append(" \"");
    sb.append(pGame.getBlack()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.RESULT_ATTR).append(" \"");
    sb.append(pGame.getResult()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.WHITE_ELO_ATTR).append(" \"");
    sb.append(pGame.getWhiteElo()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.BLACK_ELO_ATTR).append(" \"");
    sb.append(pGame.getBlackElo()).append("\"]").append("\n");
    sb.append("[").append(PGNParserConstants.ECO_ATTR).append(" \"");
    sb.append(pGame.getEco()).append("\"]").append("\n");
    return sb.toString();
  }

}
