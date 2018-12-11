/*
 * PGNWriter.java
 *
 * Copyright 2008-2018 supareno
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
 * @since 1.0
 */
public final class PGNWriter extends AbstractPGNWriter {

    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET_WITH_QUOTES = "\"]";

    @Override
    public boolean writePGNGames(final Games games) throws IllegalArgumentException {
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
     * @param pGame the current game object
     * @return a String representation of the game as pgn format
     */
    private String getAttributesStringRepresentation(final Game pGame) {
        StringBuilder sb = new StringBuilder();
        sb.append(LEFT_BRACKET).append(PGNParserConstants.EVENT_ATTR).append(" \"")
                .append(pGame.getEvent()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.SITE_ATTR).append(" \"")
                .append(pGame.getSite()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.DATE_ATTR).append(" \"")
                .append(pGame.getDate()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.ROUND_ATTR).append(" \"")
                .append(pGame.getRound()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.WHITE_ATTR).append(" \"")
                .append(pGame.getWhite()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.BLACK_ATTR).append(" \"")
                .append(pGame.getBlack()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.RESULT_ATTR).append(" \"")
                .append(pGame.getResult()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.WHITE_ELO_ATTR).append(" \"")
                .append(pGame.getWhiteElo()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.BLACK_ELO_ATTR).append(" \"")
                .append(pGame.getBlackElo()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n")
                .append(LEFT_BRACKET).append(PGNParserConstants.ECO_ATTR).append(" \"")
                .append(pGame.getEco()).append(RIGHT_BRACKET_WITH_QUOTES).append("\n");
        return sb.toString();
    }

}
