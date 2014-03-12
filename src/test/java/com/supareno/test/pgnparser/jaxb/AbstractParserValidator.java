/**
 * 
 */
package com.supareno.test.pgnparser.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.test.pgnparser.JUnitTestConstants;

/**
 * @author reno
 * 
 */
public abstract class AbstractParserValidator {

	protected void validateGames ( Games games ) {
		if ( ( games == null ) || ( games.getGame () == null ) || ( games.getGame ().size () == 0 ) ) {
			fail ( "games is null or game list is null or empty :-(" );
		}
		Game game = games.getGame ().get ( 0 );
		assertEquals ( JUnitTestConstants.REFERENCE_GAME_2_2 , game );
	}
}
