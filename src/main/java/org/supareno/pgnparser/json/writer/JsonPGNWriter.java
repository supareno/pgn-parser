/*
 * JsonPGNWriter.java
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
package org.supareno.pgnparser.json.writer;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.supareno.pgnparser.AbstractPGNWriter;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNWriterException;
import org.supareno.pgnparser.jaxb.model.Games;

import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Uses JAXB with Jettison to produce JSON.
 *
 * @author supareno
 * @since 2.3
 */
public final class JsonPGNWriter extends AbstractPGNWriter {

    @Override
    public boolean writePGNGames(Games games) throws IllegalArgumentException {
        boolean ok = true;
        BufferedWriter out = null;
        try {
            Configuration config = new Configuration();
            MappedNamespaceConvention con = new MappedNamespaceConvention(config);
            out = new BufferedWriter(new FileWriter(getFullFileName()));
            XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, out);

            Marshaller marshaller = getJaxbContext().createMarshaller();
            marshaller.marshal(games, xmlStreamWriter);
        } catch (Exception e) {
            ok = false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                throw new PGNWriterException("could not close the stream", e);
            }
        }
        return ok;
    }

    @Override
    public PGNType getExtensionType() {
        return PGNType.JSON;
    }

}
