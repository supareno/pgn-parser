/*
 * Parser.java
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
import org.supareno.pgnparser.jaxb.model.Games;

import java.io.File;
import java.io.Reader;

/**
 * The {@code Parser} interface is the root interface used to parse a PGN file.
 *
 * @author supareno
 * @since 1.0
 */
public interface Parser {

    /**
     * Parses a pgn file and returns a Games object
     *
     * @param file the filename to parse
     * @return a Games object or {@code null} if the file cannot be found
     * @throws IllegalArgumentException                         if the {@code file} parameter is {@code null} or empty
     * @throws PGNWriterException if an exception occurs during writing
     */
    Games parseFile(String file);

    /**
     * Parses a pgn file and returns a Games object
     *
     * @param file the file object to parse
     * @return a Games object or {@code null} if the file cannot be found
     * @throws IllegalArgumentException                         if the {@code file} parameter is {@code null}
     * @throws PGNWriterException if an exception occurs during writing
     */
    Games parseFile(File file);

    /**
     * Parses a pgn file associated to the reader and returns a Games object
     *
     * @param reader the reader object that contained the PGNGame(s)
     * @return a Games object or {@code null} if the file cannot be found or if the reader is
     * {@code null}
     * @throws IllegalArgumentException                         if the {@code file} parameter is {@code null} or empty
     * @throws PGNWriterException if an exception occurs during writing
     */
    Games parseFile(Reader reader);

    /**
     * Returns the extension associated to the parser.
     *
     * @return the extension associated to the parser.
     */
    PGNType getExtensionType();

}
