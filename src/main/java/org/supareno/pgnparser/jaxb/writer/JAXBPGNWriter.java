/*
 * JAXBPGNWriter.java
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
package org.supareno.pgnparser.jaxb.writer;

import org.supareno.pgnparser.AbstractPGNWriter;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNWriterException;
import org.supareno.pgnparser.jaxb.model.Games;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * The {@code JAXBPGNWriter} is a concrete JAXB implementation of the
 * {@link org.supareno.pgnparser.Writer}.
 *
 * @author supareno
 * @since 1.0
 */
public final class JAXBPGNWriter extends AbstractPGNWriter {

    @Override
    public PGNType getExtensionType() {
        return PGNType.XML;
    }

    @Override
    public boolean writePGNGames(final Games games) throws IllegalArgumentException {
        if (games == null) {
            throw new IllegalArgumentException("the PGNGame or the file name is null");
        }
        try {
            Marshaller m = getJaxbContext().createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(games, new FileOutputStream(getFullFileName()));
            return true;
        } catch (JAXBException | FileNotFoundException e) {
            throw new PGNWriterException("FileNotFoundException in writePGNGames(Games)", e);
        }
    }

}
