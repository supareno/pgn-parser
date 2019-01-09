/*
 * XMLPGNWriter.java
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
package org.supareno.pgnparser.xml.writer;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.supareno.pgnparser.AbstractPGNWriter;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNWriterException;
import org.supareno.pgnparser.model.Games;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The {@code XMLPGNWriter} is a concrete XML implementation of the
 * {@link org.supareno.pgnparser.Writer}.
 *
 * @author supareno
 * @since 3.0
 */
public final class XMLPGNWriter extends AbstractPGNWriter {

    private static final XmlMapper XML_MAPPER = new XmlMapper();
    static {
        XML_MAPPER.setDefaultUseWrapper(false);
    }

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
            XML_MAPPER.writeValue(new FileOutputStream(getFullFileName()), games);
            return true;
        } catch (IOException e) {
            throw new PGNWriterException("IOException in writePGNGames(Games)", e);
        }
    }

}
