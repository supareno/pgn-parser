/**
 *
 */
package org.supareno.test.pgnparser.jaxb.model;


import javax.xml.bind.JAXBElement;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;
import org.supareno.pgnparser.jaxb.model.Hit;
import org.supareno.pgnparser.jaxb.model.Hits;
import org.supareno.pgnparser.jaxb.model.ObjectFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * The {@code TestObjectFactory} class is used to test the {@link ObjectFactory} class.
 * @author reno
 * @version 1.0
 */
public class TestObjectFactory {

    private ObjectFactory factory = null;

    @BeforeEach
    void setUpMethod() {
        factory = new ObjectFactory();
    }

    /**
     * Tests that {@link ObjectFactory#createGames()} creates a Games object.
     */
    @Test
    void testCreateGames() {
        Games g = factory.createGames();
        assertTrue(g instanceof Games);
        assertTrue(g.getGame().isEmpty());
    }

    /**
     * Tests that {@link ObjectFactory#createGame()} creates a Game object.
     */
    @Test
    void testCreateGame() {
        Game g = factory.createGame();
        assertTrue(g instanceof Game);
    }

    /**
     * Tests that {@link ObjectFactory#createHits()} creates a Hits object.
     */
    @Test
    void testCreateHits() {
        Hits h = factory.createHits();
        assertTrue(h instanceof Hits);
    }

    /**
     * Tests that {@link ObjectFactory#createHit()} creates a Hit object.
     */
    @Test
    void testCreateHit() {
        Hit h = factory.createHit();
        assertTrue(h instanceof Hit);
    }

    /**
     * Tests that {@link ObjectFactory#createEvent(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateEvent() {
        JAXBElement<String> event = factory.createEvent("EVENT_TEST");
        assertTrue(event.getValue().equals("EVENT_TEST"));
        assertTrue(event.getName().getLocalPart().equals("event"));
    }

    /**
     * Tests that {@link ObjectFactory#createSite(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateSite() {
        JAXBElement<String> je = factory.createSite("SITE_TEST");
        assertTrue(je.getValue().equals("SITE_TEST"));
        assertTrue(je.getName().getLocalPart().equals("site"));
    }

    /**
     * Tests that {@link ObjectFactory#createDate(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateDate() {
        JAXBElement<String> je = factory.createDate("DATE_TEST");
        assertTrue(je.getValue().equals("DATE_TEST"));
        assertTrue(je.getName().getLocalPart().equals("date"));
    }

    /**
     * Tests that {@link ObjectFactory#createWhite(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateWhite() {
        JAXBElement<String> je = factory.createWhite("WHITE_TEST");
        assertTrue(je.getValue().equals("WHITE_TEST"));
        assertTrue(je.getName().getLocalPart().equals("white"));
    }

    /**
     * Tests that {@link ObjectFactory#createWhiteElo(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateWhiteElo() {
        JAXBElement<String> je = factory.createWhiteElo("WHITE-ELO_TEST");
        assertTrue(je.getValue().equals("WHITE-ELO_TEST"));
        assertTrue(je.getName().getLocalPart().equals("whiteElo"));
    }

    /**
     * Tests that {@link ObjectFactory#createBlack(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateBlack() {
        JAXBElement<String> je = factory.createBlack("BLACK_TEST");
        assertTrue(je.getValue().equals("BLACK_TEST"));
        assertTrue(je.getName().getLocalPart().equals("black"));
    }

    /**
     * Tests that {@link ObjectFactory#createBlackElo(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateBlackElo() {
        JAXBElement<String> je = factory.createBlackElo("BLACK-ELO_TEST");
        assertTrue(je.getValue().equals("BLACK-ELO_TEST"));
        assertTrue(je.getName().getLocalPart().equals("blackElo"));
    }

    /**
     * Tests that {@link ObjectFactory#createEco(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateEco() {
        JAXBElement<String> je = factory.createEco("ECO_TEST");
        assertTrue(je.getValue().equals("ECO_TEST"));
        assertTrue(je.getName().getLocalPart().equals("eco"));
    }

    /**
     * Tests that {@link ObjectFactory#createRound(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateRound() {
        JAXBElement<String> je = factory.createRound("ROUND_TEST");
        assertTrue(je.getValue().equals("ROUND_TEST"));
        assertTrue(je.getName().getLocalPart().equals("round"));
    }

    /**
     * Tests that {@link ObjectFactory#createResult(String)} creates a JAXBElement with the correct parameters.
     */
    @Test
    void testCreateResult() {
        JAXBElement<String> je = factory.createResult("RESULT_TEST");
        assertTrue(je.getValue().equals("RESULT_TEST"));
        assertTrue(je.getName().getLocalPart().equals("result"));
    }

}
