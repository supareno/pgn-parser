/*
 * JAXBPGNParser.java
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
package org.supareno.pgnparser.jaxb.parser;

import org.supareno.pgnparser.AbstractPGNParser;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.Parser;
import org.supareno.pgnparser.exception.PGNParserException;
import org.supareno.pgnparser.jaxb.model.Games;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;

/**
 * The {@code JAXBPGNParser} is the JAXB implementation of the {@link Parser} interface.
 *
 * @author supareno
 * @since 1.0
 */
public final class JAXBPGNParser extends AbstractPGNParser {

    @Override
    public PGNType getExtensionType() {
        return PGNType.XML;
    }

    @Override
    public Games parseFile(Reader reader) {
        try {
            Unmarshaller u = getJaxbContext().createUnmarshaller();
            return (Games) u.unmarshal(reader);
        } catch (JAXBException e) {
            throw new PGNParserException("error during parse", e);
        }
    }

}
