/*
 * PGNParser.java
 * 
 * Copyright 2008-2011 supareno
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Level;

import com.supareno.pgnparser.filters.PGNFileFilter;
import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.jaxb.Hit;
import com.supareno.pgnparser.jaxb.Hits;
import com.supareno.pgnparser.utils.PGNParserUtils;

/**
 * The {@code PGNParser} class is the class used to parse the PGN files.
 * 
 * @author supareno
 * @author Frank Breuel
 * @author Philip Ritchey
 * 
 * @version 2.3
 * @since 1.0
 */
public class PGNParser extends AbstractPGNParser {

	// game separator used in the first parse.
	public static final String GAME_SEPARATOR = "###";

	/** The PGNType of the Parser: set to {@link PGNType#PGN}. */
	public static final PGNType TYPE = PGNType.PGN;

	/**
	 * The String representation of the pattern used to match the attributes of
	 * the PGN file. The value of the pattern is set to {@literal \\[[^\\[]*\\]}
	 * 
	 * @see #ATTRIBUTES_PATTERN
	 */
	public static final String ATTRIBUTES_STRING_PATTERN = "\\[[^\\[]*\\]";

	/**
	 * The String representation of the pattern used to check a number validity.<br />
	 * The value is set to {@code [1-9]+([0-9]+)?}.
	 */
	public static final String NUMBER_VALIDITY_STRING_PATTERN = "[1-9]+([0-9]+)?";

	/**
	 * The String representation of the pattern used to match a single hit.<br />
	 * This pattern matches hit value like {@code e5} or {@code R8c7}.<br />
	 * The value is set to as follow (divided in multilines to a better comprehension): <br />
	 * 
	 * <pre>
	 * [a-h][x][a-h][36][e][.][p][.][\\+|#]?|	// en passant
	 * ([a-h][x])?[a-h][18]([=][RNBQ])?[\\+|#]?|	// pawn promotion
	 * [O][-][O]([-][O])?[\\+|#]?|		// castle[ ]?
	 * [RNBQK]?[a-h]?[1-8]?[x]?[a-h][1-8][\\+|#]? 	// every other move
	 * </pre>
	 * 
	 * @see #SINGLE_HIT_PATTERN
	 */
	public static final String SINGLE_HIT_STRING_PATTERN = "([a-hA-H]+[1-8]{1}[=][A-Z]{1}[\\+|#]?|" + 								// promotion
	    "[a-hrnqkA-HRNQK]{1}[1-8]{1}[\\+]?|" +									// one letter / one number pattern : simple hit
	    "[a-hA-H]{1}[1-8]?[a-hA-H]{1,3}?[1-8]{1}[\\+|#]?|" +	// complex hit
	    "[O]+[\\-][O]+[\\-][O]+[\\+|#]?|" +												// queenside castling hit
	    "[O]+[\\-][O]+[\\+|#]?|" +																// kingside castling hit
	    "[a-hpA-HP]{1}[\\-][a-hpA-HP]{1}[\\+|#]?|" +							//
	    "[RNBQK]?[a-hp]?[1-8]?[x]?[a-hp][1-8][\\+|#]?)";					//
	/**
	 * The String representation of the pattern used to match the hits of
	 * the PGN file.<br>
	 * This pattern is composed in two parts: <br>
	 * The first one is for the hit number<br>
	 * {@code #NUMBER_VALIDITY_STRING_PATTERN} concatened with {@code [.][ ]?}<br>
	 * The second one is for the hit (composed in two same part seperate with a space)<br>
	 * {@code #SINGLE_HIT_STRING_PATTERN}
	 * 
	 * @see #HITS_PATTERN
	 */
	public static final String HITS_STRING_PATTERN = NUMBER_VALIDITY_STRING_PATTERN + "[.]" + "[ ]?"
	    + SINGLE_HIT_STRING_PATTERN + "([ ]?" + SINGLE_HIT_STRING_PATTERN + "[ ]?)?";

	/**
	 * Pattern used to parse the attributes of the PGN file. It is compiled
	 * with the {@link PGNParser#ATTRIBUTES_STRING_PATTERN} pattern.
	 */
	public static final Pattern ATTRIBUTES_PATTERN = Pattern.compile ( ATTRIBUTES_STRING_PATTERN );

	/**
	 * Pattern used to parse the hits of the PGN file. It is compiled
	 * with the {@link PGNParser#HITS_STRING_PATTERN} pattern.
	 */
	public static final Pattern HITS_PATTERN = Pattern.compile ( HITS_STRING_PATTERN );

	/**
	 * Pattern used to check the validity of a Number. It is compiled with the
	 * {@link PGNParser#NUMBER_VALIDITY_STRING_PATTERN} pattern.
	 */
	public static final Pattern NUMBER_VALIDITY_PATTERN = Pattern.compile ( NUMBER_VALIDITY_STRING_PATTERN );

	/**
	 * Pattern used to parse one hit. It is compiled
	 * with the {@link PGNParser#SINGLE_HIT_STRING_PATTERN} pattern.
	 */
	public static final Pattern SINGLE_HIT_PATTERN = Pattern.compile ( SINGLE_HIT_STRING_PATTERN );

	/** Empty constructor. */
	public PGNParser () {
		this ( DEFAULT_LOGGER_LEVEL );
	}

	/**
	 * Creates a PGNParser with a Level for the Logger. This Logger will log nothing
	 * if the logger xml configuration file is not set.
	 * 
	 * @param loggerLevel the {@code Level} of the Logger. It could be:<br>
	 *          <ul>
	 *          <li>{@link Level#ALL}</li>
	 *          <li>{@link Level#DEBUG}</li>
	 *          <li>{@link Level#ERROR}</li>
	 *          <li>{@link Level#WARN}</li>
	 *          <li>{@link Level#FATAL}</li>
	 *          <li>{@link Level#INFO}</li>
	 *          <li>{@link Level#OFF}</li>
	 *          </ul>
	 */
	public PGNParser ( Level loggerLevel ) {
		this ( loggerLevel , "" );
	}

	/**
	 * Creates a PGNParser with a Level for the Logger and a configuration file
	 * for Log4j.
	 * 
	 * @param loggerLevel the {@code Level} of the Logger. It could be:<br>
	 *          <ul>
	 *          <li>{@link Level#ALL}</li>
	 *          <li>{@link Level#DEBUG}</li>
	 *          <li>{@link Level#ERROR}</li>
	 *          <li>{@link Level#WARN}</li>
	 *          <li>{@link Level#FATAL}</li>
	 *          <li>{@link Level#INFO}</li>
	 *          <li>{@link Level#OFF}</li>
	 *          </ul>
	 * @param log4jXmlConfigFile the xml log4j configuration file
	 */
	public PGNParser ( Level loggerLevel, String log4jXmlConfigFile ) {
		setLoggerLevel ( loggerLevel );
		setLoggerConfiguratorFile ( log4jXmlConfigFile );
	}

	@Override
	public String getExtension () {
		return TYPE.getExtension ();
	}

	/**
	 * Returns a List of List of PGNGames contained in the {@code folder}.
	 * <p>
	 * It will only treat the files ending with {@code PGNType.PGN.getExtension()}.
	 * </p>
	 * 
	 * @param folder the folder that contains files to parse.
	 * @return a List of List of PGNGames contained in the {@code folder}.
	 */
	public List<Games> parseFolder ( String folder ) {
		List<Games> gamesList = null;
		File file = new File ( folder );
		if ( file.exists () ) {// if the folder exists, we list it and parse each file
			File[] files = file.listFiles ( new PGNFileFilter () );
			if ( ( files != null ) && ( files.length > 0 ) ) {
				gamesList = new ArrayList<Games> ();
				for ( File f : files ) {
					gamesList.add ( parseFile ( f ) );
				}
			} else {
				log ( "empty folder :-(" );
			}
		}
		return gamesList;
	}

	/**
	 * This method parses a PGN file and formats it to be easely parseable by games.
	 * 
	 * @param reader the current Reader.
	 * @return a String representation of the content of the file.
	 */
	public String formatPGNFile ( Reader reader ) {
		StringBuffer contents = new StringBuffer ();
		String lastLine = "no";
		BufferedReader input = null;
		try {
			input = new BufferedReader ( reader );
			String line = null;
			while ( ( line = input.readLine () ) != null ) {
				if ( line.startsWith ( "[" ) && !lastLine.endsWith ( "]" ) ) {
					contents.append ( GAME_SEPARATOR );
				}
				contents.append ( line );
				contents.append ( System.getProperty ( "line.separator" ) );
				lastLine = line.trim ();// adding trim() to remove with space
			}
		} catch ( FileNotFoundException ex ) {
			log ( "error in formatting the PGN file" , ex );
		} catch ( IOException ex ) {
			log ( "error in formatting the PGN file" , ex );
		} finally {
			try {
				if ( input != null ) {
					// flush and close both "input" and its underlying Reader
					input.close ();
				}
			} catch ( IOException ex ) {
				log ( "error in formatting the PGN file" , ex );
			}
		}
		return contents.toString ();
	}

	/**
	 * Parses a PGN content in two steps. First step it parses the attributes and the second step
	 * is to parse the hits. It returns a List of PGNGames.
	 * 
	 * @param content the PGN game String representation to parse.
	 * @return a List of PGNGames.
	 */
	public Games parseContents ( String content ) {
		Games games = new Games ();
		String[] gamesString = content.split ( GAME_SEPARATOR );
		for ( String s : gamesString ) {
			if ( ( s != null ) && ( s.trim ().length () > 0 ) ) {
				String attributes = s.substring ( 0 , s.lastIndexOf ( "]" ) + 1 );
				String hits = s.substring ( s.lastIndexOf ( "]" ) + 1 , s.length () ).trim ();
				if ( ( attributes.length () > 0 ) && ( hits.length () > 0 ) ) {
					Game pgn = treatePGNString ( attributes , hits );
					if ( pgn != null ) {
						games.getGame ().add ( pgn );
					}
				}
			}
		}
		return games;
	}

	/**
	 * Parses the {@code attributes} and the {@code hits} and returns a {@code PGNGame} filled with
	 * the datas.
	 * 
	 * @param attributes the attributes of the PGN game.
	 * @param hits the hits of the PGN game.
	 * @return a PGNGame filled with the datas.
	 */
	private Game treatePGNString ( String attributes, String hits ) {
		Game p = new Game ();
		parseAttributes ( p , attributes );
		parseHits ( p , hits );
		return p;
	}

	/**
	 * Parses the PGN attributes. These attributes looks like this:<br>
	 * 
	 * <pre>
	 * [Event "event_name"]
	 * [Site "site_name"]
	 * [Date "date"]
	 * [Round "round_number"]
	 * [White "player_name"]
	 * [Black "player_name"]
	 * [Result "result"]
	 * [WhiteElo "elo_number"]
	 * [BlackElo "elo_number"]
	 * [ECO "eco"]
	 * ...
	 * </pre>
	 * 
	 * It uses the {@link PGNParser#ATTRIBUTES_PATTERN} Pattern to parse the attributes.
	 * 
	 * @param pgn the PGNGame to fill.
	 * @param attributes the String representation of the attributes to parse.
	 * 
	 * @return the PGNGame filled with the attributes found.
	 */
	private Game parseAttributes ( Game pgn, String attributes ) {
		if ( ( attributes == null ) || ( attributes.length () < 1 ) ) {
			return pgn;
		}
		Matcher matcher = ATTRIBUTES_PATTERN.matcher ( attributes );
		while ( matcher.find () ) {
			String[] str = matcher.group ().split ( "\"*\"" );
			String s1 = str[0].substring ( 1 , str[0].length () ).trim ();
			String s2 = str[1].trim ();
			setPGNGameAttributeAndValue ( pgn , s1 , s2 );
		}
		return pgn;
	}

	/**
	 * Sets the Game attribute value according to the attribute.
	 * <p>
	 * This method uses reflection to build the method according to the attribute only if the
	 * attribute is registered as a valid attribute in the {@link PGNParserConstants#ATTRIBUTES_MAP}.
	 * </p>
	 * 
	 * @param pgnGame the current Game.
	 * @param attribute the Game attribute.
	 * @param attrValue the attribute value.
	 */
	private void setPGNGameAttributeAndValue ( Game pgnGame, String attribute, String attrValue ) {

		if ( PGNParserConstants.ATTRIBUTES_MAP.containsKey ( attribute ) ) {
			if ( ( attrValue == null ) || ( attrValue.length () == 0 ) ) {
				attrValue = "?";
			}
			Method method = null;
			try {
				method = pgnGame.getClass ().getMethod ( PGNParserConstants.ATTRIBUTES_MAP.get ( attribute ) , String.class );
				method.invoke ( pgnGame , PGNParserUtils.isValidString ( attrValue ) ? attrValue : "?" );
			} catch ( SecurityException e ) {
				log ( "SecurityException on built method : " + method , e );
			} catch ( NoSuchMethodException e ) {
				log ( "NoSuchMethodException on built method : " + method , e );
			} catch ( IllegalArgumentException e ) {
				log ( "IllegalArgumentException on built method : " + method , e );
			} catch ( IllegalAccessException e ) {
				log ( "IllegalAccessException on built method : " + method , e );
			} catch ( InvocationTargetException e ) {
				log ( "InvocationTargetException on built method : " + method , e );
			}
		}

	}

	/**
	 * Parses the PGN hits. Uses the {@link #HITS_PATTERN} Pattern to parse the hits.
	 * 
	 * @param pgn the {@code PGNGame} to fill
	 * @param hits the String representation of the hits to parse
	 * @return the {@code PGNGame} filled with the attributes found
	 * @see PGNParser#HITS_PATTERN
	 */
	private Game parseHits ( Game pgn, String hits ) {
		StringBuilder newHit = new StringBuilder ();
		String[] strings = hits.split ( "\n" );
		for ( String s : strings ) {
			newHit.append ( s.trim () );
		}
		Hits list = new Hits ();
		Matcher matcher = HITS_PATTERN.matcher ( newHit.toString () );
		while ( matcher.find () ) {
			String[] str = matcher.group ().split ( "\\." );
			if ( str.length < 1 ) {
				continue;
			}
			Hit hit = new Hit ();
			hit.setNumber ( str[0] );
			hit.setContent ( normalizeHit ( str[1] ) );
			list.getHit ().add ( hit );
		}
		pgn.setHits ( list );
		return pgn;
	}

	/**
	 * Return the hit normalized.
	 * 
	 * @param hitToNormalize the to normalized
	 * @return the hit normalized.
	 */
	private String normalizeHit ( final String hitToNormalize ) {
		StringBuilder sb = new StringBuilder ();
		String[] hitSplitted = hitToNormalize.split ( " " );
		for ( String str : hitSplitted ) {
			if ( str.trim ().length () > 0 ) {
				if ( SINGLE_HIT_PATTERN.matcher ( str ).matches () ) {
					sb.append ( str ).append ( " " );
				}
			}
		}
		return sb.toString ().trim ();
	}

	@Override
	public Games parseFile ( Reader reader ) {
		String games = null;
		games = formatPGNFile ( reader );
		if ( ( games != null ) && ( games.length () > 0 ) ) {
			return parseContents ( games );
		}
		return null;
	}

}
