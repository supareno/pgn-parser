/*
 * AbstractPGNWriter.java
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

import org.supareno.pgnparser.model.Game;
import org.supareno.pgnparser.model.Games;

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
 * @since 1.0
 */
public abstract class AbstractPGNWriter extends AbstractPGNIO implements Writer {

    /**
     * Default file name: sets to {@code pgnfile}.
     * <p>
     * The extension will be added automatically by the writer
     * </p>
     */
    public static final String DEFAULT_FILE_NAME = "pgnfile";
    // name of the file
    private String filename = DEFAULT_FILE_NAME;

    @Override
    public final String getFileName() {
        return this.filename;
    }

    @Override
    public final void setFileName(final String name) {
        if (name != null && name.indexOf(".") > 0) {
            this.filename = name.substring(0, name.indexOf("."));
        }
    }

    @Override
    public final boolean writePGNGame(final Game game) throws IllegalArgumentException {
        if (game == null) {
            throw new IllegalArgumentException("the PGNGame or the file name is null");
        }
        Games games = new Games();
        games.getGames().add(game);
        return writePGNGames(games);
    }

    @Override
    public final String getFullFileName() {
        return getFileName() + "." + getExtensionType().getExtension();
    }
}
