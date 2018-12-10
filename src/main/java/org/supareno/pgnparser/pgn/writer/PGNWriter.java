/*
 * PGNWriter.java
 *
 * Copyright 2008-2014 supareno
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
package org.supareno.pgnparser.pgn.writer;

import org.supareno.pgnparser.AbstractPGNWriter;
import org.supareno.pgnparser.PGNParserConstants;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;
import org.supareno.pgnparser.jaxb.model.Hit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The {@code PGNWriter} class is used to write pgn files from PGNGames.
 *
 * @author supareno
 * @version 3.0.0
 * @since 1.0
 */
public class PGNWriter extends AbstractPGNWriter {

    public boolean writePGNGames(Games games) throws IllegalArgumentException {
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

            }
        }
        return ok;
    }

    @Override
    public PGNType getExtensionType() {
        return PGNType.PGN;
    }

    /**
     * Returns a String representation of the attrbutes of the current Game
     * object.
     *
     * @param pGame the current game object.
     */
    private String getAttributesStringRepresentation(final Game pGame) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(PGNParserConstants.EVENT_ATTR).append(" \"")
                .append(pGame.getEvent()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.SITE_ATTR).append(" \"")
                .append(pGame.getSite()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.DATE_ATTR).append(" \"")
                .append(pGame.getDate()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.ROUND_ATTR).append(" \"")
                .append(pGame.getRound()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.WHITE_ATTR).append(" \"")
                .append(pGame.getWhite()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.BLACK_ATTR).append(" \"")
                .append(pGame.getBlack()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.RESULT_ATTR).append(" \"")
                .append(pGame.getResult()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.WHITE_ELO_ATTR).append(" \"")
                .append(pGame.getWhiteElo()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.BLACK_ELO_ATTR).append(" \"")
                .append(pGame.getBlackElo()).append("\"]").append("\n")
                .append("[").append(PGNParserConstants.ECO_ATTR).append(" \"")
                .append(pGame.getEco()).append("\"]").append("\n");
        return sb.toString();
    }

}
