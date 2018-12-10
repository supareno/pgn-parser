/*
 * PGNParserConstants.java
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

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code PGNParserConstants} class defines some constants used by the
 * parsers.
 *
 * @author supareno
 * @version 1.0
 */
public final class PGNParserConstants {

    /**
     * The {@code EVENT_ATTR} String represents the <i>event</i> key: this value
     * is set to {@code Event}.
     */
    public static final String EVENT_ATTR = "Event";

    // SEVEN ROSTER TAGS
    /**
     * The {@code SITE_ATTR} String represents the <i>site</i> key: this value is
     * set to {@code Site}.
     */
    public static final String SITE_ATTR = "Site";
    /**
     * The {@code DATE_ATTR} String represents the <i>date</i> key: this value is
     * set to {@code Date}.
     */
    public static final String DATE_ATTR = "Date";
    /**
     * The {@code ROUND_ATTR} String represents the <i>round</i> key: this value
     * is set to {@code Round}.
     */
    public static final String ROUND_ATTR = "Round";
    /**
     * The {@code WHITE_ATTR} String represents the <i>white</i> key: this value
     * is set to {@code White}.
     */
    public static final String WHITE_ATTR = "White";
    /**
     * The {@code BLACK_ATTR} String represents the <i>black</i> key: this value
     * is set to {@code Black}.
     */
    public static final String BLACK_ATTR = "Black";
    /**
     * The {@code RESULT_ATTR} String represents the <i>result</i> key: this value
     * is set to {@code Result}.
     */
    public static final String RESULT_ATTR = "Result";
    /**
     * The {@code WHITE_ELO_ATTR} String represents the <i>white elo</i> key: this
     * value is set to {@code WhiteElo}.
     */
    public static final String WHITE_ELO_ATTR = "WhiteElo";

    // ADDITIONALS TAGS
    /**
     * The {@code WHITE_TITLE_ATTR} String represents the <i>white title</i> key:
     * this value is set to {@code WhiteTitle}.
     */
    public static final String WHITE_TITLE_ATTR = "WhiteTitle";
    /**
     * The {@code WHITE_USCF_ATTR} String represents the <i>white USCF</i> key:
     * this value is set to {@code WhiteUSCF}.
     */
    public static final String WHITE_USCF_ATTR = "WhiteUSCF";
    /**
     * The {@code WHITE_NA_ATTR} String represents the <i>white NA</i> key: this
     * value is set to {@code WhiteNA}.
     */
    public static final String WHITE_NA_ATTR = "WhiteNA";
    /**
     * The {@code WHITE_TYPE_ATTR} String represents the <i>white type</i> key:
     * this value is set to {@code WhiteType}.
     */
    public static final String WHITE_TYPE_ATTR = "WhiteType";
    /**
     * The {@code BLACK_ELO_ATTR} String represents the <i>black elo</i> key: this
     * value is set to {@code BlackElo}.
     */
    public static final String BLACK_ELO_ATTR = "BlackElo";
    /**
     * The {@code BLACK_TITLE_ATTR} String represents the <i>black title</i> key:
     * this value is set to {@code BlackTitle}.
     */
    public static final String BLACK_TITLE_ATTR = "BlackTitle";
    /**
     * The {@code BLACK_USCF_ATTR} String represents the <i>blask USCF</i> key:
     * this value is set to {@code BlackUSCF}.
     */
    public static final String BLACK_USCF_ATTR = "BlackUSCF";
    /**
     * The {@code BLACK_NA_ATTR} String represents the <i>black NA</i> key: this
     * value is set to {@code BlackNA}.
     */
    public static final String BLACK_NA_ATTR = "BlackNA";
    /**
     * The {@code BLACK_TYPE_ATTR} String represents the <i>black type</i> key:
     * this value is set to {@code BlackType}.
     */
    public static final String BLACK_TYPE_ATTR = "BlackType";
    /**
     * The {@code EVENT_DATE_ATTR} String represents the <i>event date</i> key:
     * this value is set to {@code EventDate}.
     */
    public static final String EVENT_DATE_ATTR = "EventDate";
    /**
     * The {@code EVENT_SPONSOR_ATTR} String represents the <i>event sponsor</i>
     * key: this value is set to {@code EventSponsor}.
     */
    public static final String EVENT_SPONSOR_ATTR = "EventSponsor";
    /**
     * The {@code SECTION_ATTR} String represents the <i>section</i> key: this
     * value is set to {@code Section}.
     */
    public static final String SECTION_ATTR = "Section";
    /**
     * The {@code STAGE_ATTR} String represents the <i>stage</i> key: this value
     * is set to {@code Stage}.
     */
    public static final String STAGE_ATTR = "Stage";
    /**
     * The {@code BOARD_ATTR} String represents the <i>board</i> key: this value
     * is set to {@code Board}.
     */
    public static final String BOARD_ATTR = "Board";
    /**
     * The {@code OPENING_ATTR} String represents the <i>opening</i> key: this
     * value is set to {@code Opening}.
     */
    public static final String OPENING_ATTR = "Opening";
    /**
     * The {@code VARIATION_ATTR} String represents the <i>variation</i> key: this
     * value is set to {@code WhiteElo}.
     */
    public static final String VARIATION_ATTR = "Variation";
    /**
     * The {@code SUBVARIATION_ATTR} String represents the <i>sub variation</i>
     * key: this value is set to {@code SubVariation}.
     */
    public static final String SUBVARIATION_ATTR = "SubVariation";
    /**
     * The {@code NIC_ATTR} String represents the <i>nic</i> key: this value is
     * set to {@code Nic}.
     */
    public static final String NIC_ATTR = "Nic";
    /**
     * The {@code TIME_ATTR} String represents the <i>time</i> key: this value is
     * set to {@code Time}.
     */
    public static final String TIME_ATTR = "Time";
    /**
     * The {@code UTCTIME_ATTR} String represents the <i>UTC time</i> key: this
     * value is set to {@code UTCTime}.
     */
    public static final String UTCTIME_ATTR = "UTCTime";
    /**
     * The {@code UTCDATE_ATTR} String represents the <i>UTC date</i> key: this
     * value is set to {@code UTCDate}.
     */
    public static final String UTCDATE_ATTR = "UTCDate";
    /**
     * The {@code TIME_CONTROL_ATTR} String represents the <i>time control</i>
     * key: this value is set to {@code TimeControl}.
     */
    public static final String TIME_CONTROL_ATTR = "TimeControl";
    /**
     * The {@code SETUP_ATTR} String represents the <i>set up</i> key: this value
     * is set to {@code SetUp}.
     */
    public static final String SETUP_ATTR = "SetUp";
    /**
     * The {@code FEN_ATTR} String represents the <i>FEN</i> key: this value is
     * set to {@code FEN}.
     */
    public static final String FEN_ATTR = "FEN";
    /**
     * The {@code TERMINATION_ATTR} String represents the <i>termination</i> key:
     * this value is set to {@code Termination}.
     */
    public static final String TERMINATION_ATTR = "Termination";
    /**
     * The {@code ECO_ATTR} String represents the <i>eco</i> key: this value is
     * set to {@code Eco}.
     */
    public static final String ECO_ATTR = "Eco";
    public static final Map<String, String> ATTRIBUTES_MAP = new HashMap<String, String>();

    static {
        ATTRIBUTES_MAP.put(EVENT_ATTR, "set" + EVENT_ATTR);
        ATTRIBUTES_MAP.put(SITE_ATTR, "set" + SITE_ATTR);
        ATTRIBUTES_MAP.put(DATE_ATTR, "set" + DATE_ATTR);
        ATTRIBUTES_MAP.put(ROUND_ATTR, "set" + ROUND_ATTR);
        ATTRIBUTES_MAP.put(WHITE_ATTR, "set" + WHITE_ATTR);
        ATTRIBUTES_MAP.put(BLACK_ATTR, "set" + BLACK_ATTR);
        ATTRIBUTES_MAP.put(RESULT_ATTR, "set" + RESULT_ATTR);
        // ADDITIONALS TAGS
        ATTRIBUTES_MAP.put(WHITE_ELO_ATTR, "set" + WHITE_ELO_ATTR);
        ATTRIBUTES_MAP.put(WHITE_TITLE_ATTR, "set" + WHITE_TITLE_ATTR);
        ATTRIBUTES_MAP.put(WHITE_USCF_ATTR, "set" + WHITE_USCF_ATTR);
        ATTRIBUTES_MAP.put(WHITE_NA_ATTR, "set" + WHITE_NA_ATTR);
        ATTRIBUTES_MAP.put(WHITE_TYPE_ATTR, "set" + WHITE_TYPE_ATTR);
        ATTRIBUTES_MAP.put(BLACK_ELO_ATTR, "set" + BLACK_ELO_ATTR);
        ATTRIBUTES_MAP.put(BLACK_TITLE_ATTR, "set" + BLACK_TITLE_ATTR);
        ATTRIBUTES_MAP.put(BLACK_USCF_ATTR, "set" + BLACK_USCF_ATTR);
        ATTRIBUTES_MAP.put(BLACK_NA_ATTR, "set" + BLACK_NA_ATTR);
        ATTRIBUTES_MAP.put(BLACK_TYPE_ATTR, "set" + BLACK_TYPE_ATTR);
        ATTRIBUTES_MAP.put(EVENT_DATE_ATTR, "set" + EVENT_DATE_ATTR);
        ATTRIBUTES_MAP.put(EVENT_SPONSOR_ATTR, "set" + EVENT_SPONSOR_ATTR);
        ATTRIBUTES_MAP.put(SECTION_ATTR, "set" + SECTION_ATTR);
        ATTRIBUTES_MAP.put(STAGE_ATTR, "set" + STAGE_ATTR);
        ATTRIBUTES_MAP.put(BOARD_ATTR, "set" + BOARD_ATTR);
        ATTRIBUTES_MAP.put(OPENING_ATTR, "set" + OPENING_ATTR);
        ATTRIBUTES_MAP.put(VARIATION_ATTR, "set" + VARIATION_ATTR);
        ATTRIBUTES_MAP.put(SUBVARIATION_ATTR, "set" + SUBVARIATION_ATTR);
        ATTRIBUTES_MAP.put(NIC_ATTR, "set" + NIC_ATTR);
        ATTRIBUTES_MAP.put(TIME_ATTR, "set" + TIME_ATTR);
        ATTRIBUTES_MAP.put(UTCTIME_ATTR, "set" + UTCTIME_ATTR);
        ATTRIBUTES_MAP.put(UTCDATE_ATTR, "set" + UTCDATE_ATTR);
        ATTRIBUTES_MAP.put(TIME_CONTROL_ATTR, "set" + TIME_CONTROL_ATTR);
        ATTRIBUTES_MAP.put(SETUP_ATTR, "set" + SETUP_ATTR);
        ATTRIBUTES_MAP.put(FEN_ATTR, "set" + FEN_ATTR);
        ATTRIBUTES_MAP.put(TERMINATION_ATTR, "set" + TERMINATION_ATTR);
    }

    private PGNParserConstants() {
    }
}
