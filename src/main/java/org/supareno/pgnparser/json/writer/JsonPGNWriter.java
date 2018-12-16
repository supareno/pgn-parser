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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.supareno.pgnparser.AbstractPGNWriter;
import org.supareno.pgnparser.PGNType;
import org.supareno.pgnparser.exception.PGNWriterException;
import org.supareno.pgnparser.model.Games;

import java.io.FileOutputStream;

/**
 * Uses Jackson's {@link ObjectMapper} to produce JSON.
 *
 * @author supareno
 * @since 2.3
 */
public final class JsonPGNWriter extends AbstractPGNWriter {

    @Override
    public boolean writePGNGames(final Games games) throws IllegalArgumentException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new FileOutputStream(getFullFileName()), games);
            return true;
        } catch (Exception e) {
            throw new PGNWriterException("Error during JSON writing", e);
        }
    }

    @Override
    public PGNType getExtensionType() {
        return PGNType.JSON;
    }

}
