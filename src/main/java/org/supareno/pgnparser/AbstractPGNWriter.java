/*
 * AbstractPGNWriter.java
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
package org.supareno.pgnparser;

import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code AbstractPGNWriter} abstract class defines some methods used by all
 * the writers.
 * <p>
 * Defines the {@link #writePGNGame(Game)} by calling the
 * {@link #writePGNGames(Games)} method with the game parameter filled in a
 * Games object.
 * </p>
 *
 * @author supareno
 * @version 3.0.0
 * @since 1.0
 */
public abstract class AbstractPGNWriter extends AbstractPGNIO implements Writer {

    /**
     * The {@code DATEFORMAT} SimpleDateFormat is used to format date file name:
     * the value is set to {@literal yyyMMddHHmmss}.
     */
    public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    /**
     * Default file name: sets to {@code pgnfile} with the date of the creation
     * added at the end. The format of the date is:
     * <p>
     * {@code yyyyMMddHHmmss}
     * </p>
     * The extension will be added automatically by the writer.
     */
    public static final String DEFAULT_FILE_NAME = "pgnfile";
    // name of the file
    private String filename = DEFAULT_FILE_NAME;

    /**
     * Formats a Date and returns it to a String representation using the
     * {@link #DATEFORMAT} SimpleDateFormat pattern. A new Date is used at every
     * call.
     *
     * @return a String representation of a Date using the {@link #DATEFORMAT}
     * SimpleDateFormat pattern.
     * @see #formatDate(SimpleDateFormat)
     */
    protected static synchronized String formatDate() {
        return formatDate(DATEFORMAT);
    }

    /**
     * Formats a Date and returns it to a String representation using the
     * {@code sdf} SimpleDateFormat pattern. A new Date is used at every call.
     *
     * @param sdf the SimpleDateFormat used for the formatting
     * @return a String representation of a Date using the {@code format} pattern
     */
    protected static synchronized String formatDate(SimpleDateFormat sdf) {
        return sdf.format(new Date());
    }

    @Override
    public final String getFileName() {
        return this.filename;
    }

    @Override
    public final void setFileName(String filename) {
        String fname = filename;
        if (filename.indexOf(".") > 0) {
            fname = filename.substring(0, filename.indexOf("."));
        }
        this.filename = fname;
    }

    @Override
    public final boolean writePGNGame(Game game) throws IllegalArgumentException {
        if (game == null) {
            throw new IllegalArgumentException("the PGNGame or the file name is null");
        }
        Games games = new Games();
        games.getGame().add(game);
        return writePGNGames(games);
    }

    @Override
    public final String getFullFileName() {
        return getFileName() + "." + getExtensionType().getExtension();
    }
}
