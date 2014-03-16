/*
 * Parser.java
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
package com.supareno.pgnparser;

import java.io.File;
import java.io.Reader;
import java.net.URL;

import com.supareno.pgnparser.jaxb.Games;

/**
 * The {@code Parser} interface is the root interface used to parse a PGN file.
 * 
 * @author reno
 * 
 * @version 2.3.0
 * 
 * @since 1.0
 */
public interface Parser extends Loggable {

  /**
   * Parses a pgn file and returns a Games object.
   * 
   * @param file the filename to parse
   * @return a Games object or {@code null} if the file cannot be found.
   */
  Games parseFile ( String file );

  /**
   * Parses a pgn file and returns a Games object.
   * 
   * @param file the file object to parse
   * @return a Games object or {@code null} if the file cannot be found.
   */
  Games parseFile ( File file );

  /**
   * Parses a pgn file located at the specified URL and returns a Games object.
   * 
   * @param url the URL location of the file to parse.
   * @return a Games object or {@code null} if the file cannot be found.
   */
  Games parseURL ( String url );

  /**
   * Parses a pgn file located at the specified URL and returns a Games object.
   * 
   * @param url the URL object location of the file to parse.
   * @return a Games object or {@code null} if the file cannot be found.
   */
  Games parseURL ( URL url );

  /**
   * Parses a pgn file associated to the reader and returns a Games object.
   * 
   * @param reader the reader object that contained the PGNGame(s).
   * @return a Games object or {@code null} if the file cannot be found or if the reader is
   *         {@code null}.
   */
  Games parseFile ( Reader reader );

  /**
   * Returns the extension associated to the parser.
   * <p>
   * This extension corresponds to the extension of the {@link PGNType} associated to the parser.
   * </p>
   * 
   * @return the extension associated to the parser.
   */
  String getExtension ();

}
