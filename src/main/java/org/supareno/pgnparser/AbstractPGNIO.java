/*
 * AbstractPGNIO.java
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
package org.supareno.pgnparser;

import org.supareno.pgnparser.jaxb.model.Games;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Abstract class for writing or parsing PGN files, urls, ...
 * <p>
 * It initializes a JAXBContext class based on the Games class.
 * </p>
 *
 * @author supareno
 * @since 2.3
 */
public abstract class AbstractPGNIO {


    private static JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(Games.class);
        } catch (JAXBException e) {
            // Should never happen ....
            throw new ExceptionInInitializerError("Could not initialize the JAXBContext with 'org.supareno.pgnparser.jaxb'");
        }
    }

    /**
     * Return the current JAXBContext
     *
     * @return the current JAXBContext
     */
    protected static JAXBContext getJaxbContext() {
        return JAXB_CONTEXT;
    }
}
