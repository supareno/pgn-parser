/**
 * 
 */
package com.supareno.test.pgnparser.jaxb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBElement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.jaxb.Hit;
import com.supareno.pgnparser.jaxb.Hits;
import com.supareno.pgnparser.jaxb.ObjectFactory;


/**
 * The {@code TestObjectFactory} class is used to test the {@link ObjectFactory} class.
 * @author reno
 * @version 1.0
 */
public class TestObjectFactory {

	private ObjectFactory factory = null;

	@Before
	public void setUpMethod(){
		factory = new ObjectFactory();
	}

	/**
	 * Tests that {@link ObjectFactory#createGames()} creates a Games object.
	 */
	@Test
	public void testCreateGames(){
		Games g = factory.createGames();
		assertTrue(g instanceof Games);
		assertTrue(g.getGame().isEmpty());
	}

	/**
	 * Tests that {@link ObjectFactory#createGame()} creates a Game object.
	 */
	@Test
	public void testCreateGame(){
		Game g = factory.createGame();
		assertTrue(g instanceof Game);
	}

	/**
	 * Tests that {@link ObjectFactory#createHits()} creates a Hits object.
	 */
	@Test
	public void testCreateHits(){
		Hits h = factory.createHits();
		assertTrue(h instanceof Hits);
	}

	/**
	 * Tests that {@link ObjectFactory#createHit()} creates a Hit object.
	 */
	@Test
	public void testCreateHit(){
		Hit h = factory.createHit();
		assertTrue(h instanceof Hit);
	}

	/**
	 * Tests that {@link ObjectFactory#createEvent(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateEvent(){
		JAXBElement<String> event = factory.createEvent("EVENT_TEST");
		assertNotNull(event);
		assertTrue(event.getValue().equals("EVENT_TEST"));
		assertTrue(event.getName().getLocalPart().equals("event"));
	}

	/**
	 * Tests that {@link ObjectFactory#createSite(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateSite(){
		JAXBElement<String> je = factory.createSite("SITE_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("SITE_TEST"));
		assertTrue(je.getName().getLocalPart().equals("site"));
	}

	/**
	 * Tests that {@link ObjectFactory#createDate(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateDate(){
		JAXBElement<String> je = factory.createDate("DATE_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("DATE_TEST"));
		assertTrue(je.getName().getLocalPart().equals("date"));
	}

	/**
	 * Tests that {@link ObjectFactory#createWhite(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateWhite(){
		JAXBElement<String> je = factory.createWhite("WHITE_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("WHITE_TEST"));
		assertTrue(je.getName().getLocalPart().equals("white"));
	}

	/**
	 * Tests that {@link ObjectFactory#createWhiteElo(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateWhiteElo(){
		JAXBElement<String> je = factory.createWhiteElo("WHITE-ELO_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("WHITE-ELO_TEST"));
		assertTrue(je.getName().getLocalPart().equals("whiteElo"));
	}

	/**
	 * Tests that {@link ObjectFactory#createBlack(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateBlack(){
		JAXBElement<String> je = factory.createBlack("BLACK_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("BLACK_TEST"));
		assertTrue(je.getName().getLocalPart().equals("black"));
	}

	/**
	 * Tests that {@link ObjectFactory#createBlackElo(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateBlackElo(){
		JAXBElement<String> je = factory.createBlackElo("BLACK-ELO_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("BLACK-ELO_TEST"));
		assertTrue(je.getName().getLocalPart().equals("blackElo"));
	}

	/**
	 * Tests that {@link ObjectFactory#createEco(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateEco(){
		JAXBElement<String> je = factory.createEco("ECO_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("ECO_TEST"));
		assertTrue(je.getName().getLocalPart().equals("eco"));
	}

	/**
	 * Tests that {@link ObjectFactory#createRound(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateRound(){
		JAXBElement<String> je = factory.createRound("ROUND_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("ROUND_TEST"));
		assertTrue(je.getName().getLocalPart().equals("round"));
	}
	/**
	 * Tests that {@link ObjectFactory#createResult(String)} creates a JAXBElement with the correct parameters.
	 */
	@Test
	public void testCreateResult(){
		JAXBElement<String> je = factory.createResult("RESULT_TEST");
		assertNotNull(je);
		assertTrue(je.getValue().equals("RESULT_TEST"));
		assertTrue(je.getName().getLocalPart().equals("result"));
	}

	@After
	public void cleanUpClass(){
		factory = null;
	}
}
