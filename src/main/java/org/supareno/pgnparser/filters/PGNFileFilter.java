/*
 * PGNFileFilter.java
 *
 * Copyright 2008-2018 supareno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.supareno.pgnparser.filters;

import org.supareno.pgnparser.PGNType;

import java.io.File;
import java.io.FileFilter;

/**
 * This class is a specific file filter for PGN files.
 * <p>
 * Return {@code true} if the file name ends with {@link PGNType#PGN#getExtension()}to lower case, {@code false}
 * otherwise.
 * </p>
 *
 * @author supareno
 * @version 3.0.0
 */
public final class PGNFileFilter implements FileFilter {

    private static final int DOT_N_ENTENSION_LENGTH = 4;

    @Override
    public boolean accept(final File pathname) {
        if (pathname == null) {
            return false;
        }
        return isEndWithPGN(pathname);
    }

    /**
     * @param pathname the name of the file
     * @return true if pathname ends with 'pgn', false otherwise
     */
    private boolean isEndWithPGN(final File pathname) {
        if (pathname.getName().toLowerCase().endsWith(PGNType.PGN.getExtension())) {
            return isPGNExtension(pathname);
        }
        return false;
    }

    /**
     * @param pathname the pathname to check
     * @return true if pathname ends with pgn, false otherwise
     */
    private boolean isPGNExtension(final File pathname) {
        int x = pathname.getName().indexOf(".");
        return pathname.getName().substring(x + 1, x + DOT_N_ENTENSION_LENGTH).equalsIgnoreCase((PGNType.PGN.getExtension()));
    }

}
