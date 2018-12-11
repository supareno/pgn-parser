/*
 * JsonPGNParser.java
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
package org.supareno.pgnparser.json.parser;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.supareno.pgnparser.AbstractPGNParser;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNParserException;
import org.supareno.pgnparser.jaxb.model.Games;

import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * The JSON parser could parse JSON files with a single game or a game's array.
 * <p>
 * It uses JAXB with Jettison to parse JSON. The supported JSON represent is:
 *
 * <pre>
 * <code>
 * {"games" :
 *     {"game":
 *       [
 *         {
 *           "event": "",
 *           "site": "",
 *           "date": "",
 *           "round": "",
 *           "white": "",
 *           "black": "",
 *           "result": "",
 *           "whiteTitle":"",
 *           "whiteElo":"",
 *           "whiteUSCF":"",
 *           "whiteNA":"",
 *           "whiteType":"",
 *           "blackTitle":"",
 *           "blackElo":"",
 *           "blackUSCF":"",
 *           "blackNA":"",
 *           "blackType":"",
 *           "eventDate":"",
 *           "eventSponsor":"",
 *           "section":"",
 *           "stage":"",
 *           "board":"",
 *           "opening":"",
 *           "variation":"",
 *           "subVariation":"",
 *           "eco":"",
 *           "nic":"",
 *           "time":"",
 *           "UTCTime":"",
 *           "UTCDate":"",
 *           "timeControl":"",
 *           "setUp":"",
 *           "fEN":"",
 *           "termination":"",
 *           "hits":
 *             {"hit":
 *                 [
 *                   {"@number":"","$":""}
 *                 ]
 *             }
 *         }
 *     ]
 *   }
 * }
 *
 * </code>
 * </pre>
 *
 * </p>
 *
 * @author supareno
 * @since 2.3
 */
public final class JsonPGNParser extends AbstractPGNParser {

    @Override
    public Games parseFile(final Reader reader) {
        Games games = null;
        try {
            JSONObject obj = new JSONObject(readJSONFile(reader));
            Configuration config = new Configuration();
            MappedNamespaceConvention con = new MappedNamespaceConvention(config);
            XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);
            Unmarshaller unmarshaller = getJaxbContext().createUnmarshaller();
            games = (Games) unmarshaller.unmarshal(xmlStreamReader);
        } catch (Exception e) {
            throw new PGNParserException("Error during parsing", e);
        }
        return games;
    }

    /**
     * @param reader the current reader
     * @return a String representation of the file
     * @throws PGNParserException if an exception occurs during parsing
     */
    public String readJSONFile(final Reader reader) {
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader(reader);
            String line = null;
            while ((line = input.readLine()) != null) {
                contents.append(line);
            }
        } catch (IOException ex) {
            throw new PGNParserException("Error during parsing", ex);
        } finally {
            try {
                if (input != null) {
                    // flush and close both "input" and its underlying Reader
                    input.close();
                }
            } catch (IOException ex) {

                throw new PGNParserException("Error during parsing", ex);
            }
        }
        return contents.toString();
    }

    @Override
    public PGNType getExtensionType() {
        return PGNType.JSON;
    }

}
