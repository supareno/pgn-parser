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

import org.apache.log4j.Level;

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

	/** The PGNType of the current Writer which is PGN. */
	public static final PGNType TYPE = PGNType.PGN;

	/** Empty constructor. */
	public PGNWriter() { }
	/**
	 * Creates a new XMLPGNWriter with a Level for the Logger.
	 * 
	 * @param loggerLevel the {@code Level} of the Logger. It could be:<br>
	 * <ul><li>{@link Level#ALL}</li>
	 * <li>{@link Level#DEBUG}</li>
	 * <li>{@link Level#ERROR}</li>
	 * <li>{@link Level#WARN}</li>
	 * <li>{@link Level#FATAL}</li>
	 * <li>{@link Level#INFO}</li>
	 * <li>{@link Level#OFF}</li></ul>
	 */
	public PGNWriter(Level logLevel){
		this(logLevel, DEFAULT_FILE_NAME+formatDate(),"");
	}

	/**
	 * Creates a new {@code XMLPGNWriter} with a specific name and a Level
	 * for the Logger.
	 * 
	 * @param loggerLevel the {@code Level} of the Logger. It could be:<br>
	 * <ul><li>{@link Level#ALL}</li>
	 * <li>{@link Level#DEBUG}</li>
	 * <li>{@link Level#ERROR}</li>
	 * <li>{@link Level#WARN}</li>
	 * <li>{@link Level#FATAL}</li>
	 * <li>{@link Level#INFO}</li>
	 * <li>{@link Level#OFF}</li></ul>
	 * @param file the name of the file to create (without the extension)
	 */
	public PGNWriter(Level logLevel, String file){
		this(logLevel,file,"");
	}

	/**
	 * Creates a new {@code XMLPGNWriter} with a specific name, a Level
	 * for the Logger and an xml configuration file.
	 * 
	 * @param loggerLevel the {@code Level} of the Logger. It could be:<br>
	 * <ul><li>{@link Level#ALL}</li>
	 * <li>{@link Level#DEBUG}</li>
	 * <li>{@link Level#ERROR}</li>
	 * <li>{@link Level#WARN}</li>
	 * <li>{@link Level#FATAL}</li>
	 * <li>{@link Level#INFO}</li>
	 * <li>{@link Level#OFF}</li></ul>
	 * @param file the name of the file to create (without the extension)
	 * @param xmlConfigurationFile the xml configuration file used to configure the Logger.
	 */
	public PGNWriter(Level logLevel, String file, String xmlConfigurationFile){
		setLoggerLevel(logLevel);
		setFileName(file);
		setLoggerConfiguratorFile(xmlConfigurationFile);
	}
	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#writePGNGame(com.supareno.pgnparser.PGNGame)
	 */
	public boolean writePGNGame(Game game)  throws IllegalArgumentException {
		if (game == null ) {
			throw new IllegalArgumentException("the PGNGame or the file name is null");
		}
		Games games = new Games();
		games.getGame().add(game);
		return writePGNGames(games);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#writePGNGames(java.util.List)
	 */
	public boolean writePGNGames(Games games) throws IllegalArgumentException{
		if (games == null ) {
			throw new IllegalArgumentException("the PGNGame or the file name is null");
		}
		boolean ok = true;
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(getFileName() + "." + getExtension()));
			for (Game game : games.getGame()) {
				if (game == null) { continue; }
				out.write("\n");
				// writing attributes
				out.write(getAttributesStringRepresentation(game));
				// writing hits & result
				List<Hit> hits = game.getHits().getHit();
				if (hits != null && hits.size() > 0) {
					for(Hit hit : hits) {
						out.write(hit.getNumber() + "." + hit.getContent());
						out.write(" ");
					}
				}
			}
		}catch(Exception e){
			ok = false;
		} finally {
			try{
				out.close();
			} catch (IOException e){
				ok = false;
				log("cannot close the writer !");
			}
		}
		return ok;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#getExtension()
	 */
	public String getExtension(){ return TYPE.getExtension(); }

	/**
	 * Returns a String representation of the attrbutes of the current Game object.
	 * @param pGame the current game object.
	 */
	private String getAttributesStringRepresentation(final Game pGame) {
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
