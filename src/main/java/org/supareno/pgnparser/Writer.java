/*
 * Writer.java
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
package org.supareno.pgnparser;

import org.supareno.pgnparser.exception.PGNWriterException;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;

/**
 * The {@code Writer} class is the root interface of the PGN writers.
 * <p>
 * It uses the JAXB generated classes ({@link Games}, {@link Game}, ...) to generate PGN files.
 * These files could be {@code pgn} files (with the {@link org.supareno.pgnparser.pgn.writer.PGNWriter}),
 * {@code xml} files (with the {@link org.supareno.pgnparser.jaxb.writer.JAXBPGNWriter}) or
 * {@code json} files (with the {@link org.supareno.pgnparser.json.writer.JsonPGNWriter}).
 * </p>
 *
 * @author reno
 * @since 1.0
 */
public interface Writer {

    /**
     * Returns {@code true} if the game has been written, {@code false} otherwise
     *
     * @param game the game to write
     * @return {@code true} if the game has been written, {@code false} otherwise
     * @throws IllegalArgumentException                         if the {@code game} parameter is {@code null}
     * @throws PGNWriterException if an exception occurs during writing
     */
    boolean writePGNGame(Game game);

    /**
     * Returns {@code true} if the games has been written, {@code false} otherwise
     *
     * @param games the games to write
     * @return {@code true} if the list of the games has been written, {@code false} otherwise
     * @throws IllegalArgumentException                         if the {@code game} parameter is {@code null}
     * @throws PGNWriterException if an exception occurs during writing
     */
    boolean writePGNGames(Games games);

    /**
     * Returns the name of the file to write.
     *
     * @return the name of the file to write.
     */
    String getFileName();

    /**
     * Sets the name of the file to write.
     *
     * @param filename the name of the file.
     */
    void setFileName(String filename);

    /**
     * Returns the extension of the file to write.
     *
     * @return the extension of the file to write.
     */
    PGNType getExtensionType();

    /**
     * @return the result of the concatenation of {@link #getFileName()}, a dot
     * and {@link #getExtensionType()}
     */
    String getFullFileName();
}
