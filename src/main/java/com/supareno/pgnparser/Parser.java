/*
 * Parser.java
 * 
 * Copyright 2008-2011 supareno
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  	http://www.apache.org/licenses/LICENSE-2.0
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

import org.apache.log4j.Level;

import com.supareno.pgnparser.jaxb.Games;

/**
 * The {@code Parser} interface is the root interface used to parse a PGN file.
 * @author reno
 * @version 2.0
 */
public interface Parser {

	/**
	 * Parses a pgn file and returns a Games object.
	 * @param file the filename to parse
	 * @return a Games object or {@code null} if the file cannot be found.
	 */
	Games parseFile(String file);

	/**
	 * Parses a pgn file and returns a Games object.
	 * @param file the file object to parse
	 * @return a Games object or {@code null} if the file cannot be found.
	 */
	Games parseFile(File file);

	/**
	 * Parses a pgn file located at the specified URL and returns a Games object.
	 * @param url the URL location of the file to parse.
	 * @return a Games object or {@code null} if the file cannot be found.
	 */
	Games parseURL(String url);

	/**
	 * Parses a pgn file located at the specified URL and returns a Games object.
	 * @param url the URL object location of the file to parse.
	 * @return a Games object or {@code null} if the file cannot be found.
	 */
	Games parseURL(URL url);

	/**
	 * Parses a pgn file associated to the reader and returns a Games object.
	 * @param reader the reader object that contained the PGNGame(s).
	 * @return a Games object or {@code null} if the file cannot be found or if the reader is {@code null}.
	 */
	Games parseFile(Reader reader);

	/**
	 * Returns the Log4j Level. This Level could be:<br>
	 * <ul><li>{@link Level#ALL}</li>
	 * <li>{@link Level#DEBUG}</li>
	 * <li>{@link Level#ERROR}</li>
	 * <li>{@link Level#WARN}</li>
	 * <li>{@link Level#FATAL}</li>
	 * <li>{@link Level#INFO}</li>
	 * <li>{@link Level#OFF}</li></ul>
	 * @return the Log4j Level.
	 */
	Level getLoggerLevel();

	/**
	 * Sets the Log4j Level. This Level could be:<br>
	 * <ul><li>{@link Level#ALL}</li>
	 * <li>{@link Level#DEBUG}</li>
	 * <li>{@link Level#ERROR}</li>
	 * <li>{@link Level#WARN}</li>
	 * <li>{@link Level#FATAL}</li>
	 * <li>{@link Level#INFO}</li>
	 * <li>{@link Level#OFF}</li></ul>
	 * @param lev the Log4j Level.
	 */
	void setLoggerLevel(Level lev);

	/**
	 * Sets the Log4j configuration file which can be an xml file or a properties file.
	 * @param file the name of the Log4j file configuration.
	 */
	void setLoggerConfiguratorFile(String file);

	/**
	 * Returns the extension associated to the parser.
	 * <p>
	 * This extension corresponds to the extension of the {@link PGNType} associated to the parser.
	 * </p>
	 * @return the extension associated to the parser.
	 */
	String getExtension();

	/**
	 * Logs the message using the Logger with the Level set.
	 * @param msg the message to lo;
	 */
	void log(String msg);

	/**
	 * Logs the message and the Throwable using the Logger with the Level set
	 * in the constructor.
	 * @param msg the message to log
	 * @param t the Throwable to log
	 */
	void log(String msg, Throwable t);
}
