/*
 * PGNParserUtils.java
 *
 * Copyright 2008-2014 supareno
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
package org.supareno.pgnparser.utils;

/**
 * This is a utility class.
 *
 * @author supareno
 * @since 2.2
 */
public final class PGNParserUtils {

    /**
     * Blank String.
     */
    public static final String BLANK = "";

    /**
     * Question mark punctuation String.
     */
    public static final String QUESTION_MARK = "?";

    /**
     * Game separator used in the first parse. The value is set to {@code ###}
     */
    public static final String GAME_SEPARATOR = "###";

    /**
     * Return {@code true} if {@code str} is not null and if its length is
     * superior to {@code 0}. Otherwise returns {@code false}.
     *
     * @param str the string to check.
     * @return {@code true} if {@code str} is not null and if its length is
     * superior to {@code 0}. Otherwise returns {@code false}.
     */
    public static boolean isValidString(String str) {
        return (str != null) && (str.length() > 0);
    }

    /**
     * @param value the value to check
     * @return value if valid. Otherwise return {@value #BLANK}
     */
    public static String getSafeValue(String value) {
        return PGNParserUtils.isValidString(value) ? value : BLANK;
    }
}
