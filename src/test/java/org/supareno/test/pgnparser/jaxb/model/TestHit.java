/*
 * TestHit.java
 *
 * Copyright 2008-2014 supareno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.supareno.test.pgnparser.jaxb.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.model.Hit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * The {@code TestHit} class is used to test the {@link Hit} class.
 *
 * @author reno
 * @version 1.1
 */
public class TestHit {

    private Hit hit1 = null;
    private Hit hit2 = null;

    @BeforeEach
    void setUpMethod() {
        hit1 = new Hit();
        hit1.setNumber("1");
        hit1.setContent("e4 e5");
        hit2 = new Hit();
        hit2.setNumber("1");
        hit2.setContent("e4 e5");
    }

    /**
     * Tests Hit equality.
     */
    @Test
    void testEquality() {
        assertEquals(hit1, hit2);
        assertEquals(hit1, hit1);
        assertEquals(hit2, hit1);
        assertEquals(hit2, hit2);
    }

    /**
     * Tests Hit difference.
     */
    @Test
    void testNotEqual() {
        hit2.setContent("e4 e6");
        assertNotSame(hit1, hit2);
    }

    /**
     * Tests the getHitSeparated() method.
     */
    @Test
    void testGetHitSeparated() {
        String[] hitSeparated = hit1.getHitSeparated();
        assertEquals(hitSeparated[0], "e4");
        assertEquals(hitSeparated[1], "e5");
    }

}
