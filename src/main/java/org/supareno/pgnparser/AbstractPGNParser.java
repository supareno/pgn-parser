/*
 * AbstractPGNParser.java
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

import org.supareno.pgnparser.exception.PGNParserException;
import org.supareno.pgnparser.model.Game;
import org.supareno.pgnparser.model.Games;
import org.supareno.pgnparser.utils.PGNParserUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The {@code AbstractPGNParser} class is the abstract implementation of the
 * {@link Parser} interface. It defines few methods used by the parsers.
 *
 * @author supareno
 * @since 1.0
 */
public abstract class AbstractPGNParser extends AbstractPGNIO implements Parser {

    @Override
    public final Games parseFile(final String file) {
        return parseFile(new File(file));
    }

    @Override
    public final Games parseFile(final File file) {
        Reader r = null;
        try {
            r = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file not found", e);
        }
        return parseFile(r);
    }

    /**
     * Sets the Game attribute value according to the attribute.
     * <p>
     * This method uses reflection to build the method according to the attribute
     * only if the attribute is registered as a valid attribute in the
     * {@link PGNParserConstants#ATTRIBUTES_MAP}.
     * </p>
     * <p>
     * If {@code attrValue} is not value, the value set is
     * {@link PGNParserUtils#QUESTION_MARK}
     * </p>
     *
     * @param pgnGame   the current Game.
     * @param attribute the Game attribute.
     * @param attrValue the attribute value.
     * @see #setPGNGameAttributeAndValue(Game, String, String, String)
     */
    protected final void setPGNGameAttributeAndValue(final Game pgnGame, final String attribute, final String attrValue) {
        setPGNGameAttributeAndValue(pgnGame, attribute, attrValue,
                PGNParserUtils.QUESTION_MARK);
    }

    /**
     * Sets the Game attribute value according to the attribute.
     * <p>
     * This method uses reflection to build the method according to the attribute
     * only if the attribute is registered as a valid attribute in the
     * {@link PGNParserConstants#ATTRIBUTES_MAP}.
     * </p>
     *
     * @param pgnGame          the current Game.
     * @param attribute        the Game attribute.
     * @param attrValue        the attribute value.
     * @param replacementValue the value to use if the attribute's value is not
     *                         valid
     * @throws PGNParserException if an exception occurs during game attribute settings. Should never happen.
     */
    protected final void setPGNGameAttributeAndValue(final Game pgnGame, final String attribute,
                                                     final String attrValue, final String replacementValue) {
        if (PGNParserConstants.ATTRIBUTES_MAP.containsKey(attribute)) {
            Method method = null;
            try {
                method = pgnGame.getClass().getMethod(
                        PGNParserConstants.ATTRIBUTES_MAP.get(attribute), String.class);
                method.invoke(pgnGame,
                        PGNParserUtils.isValidString(attrValue) ? attrValue
                                : replacementValue);
            } catch (SecurityException | NoSuchMethodException | IllegalArgumentException
                    | IllegalAccessException | InvocationTargetException e) {
                throw new PGNParserException("Exception on built method : " + method, e);
            }
        }
    }
}
