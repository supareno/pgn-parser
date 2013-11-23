/*
 * TestHits.java
 * 
 * Copyright 2008-2011 supareno
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
package com.supareno.test.pgnparser.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supareno.pgnparser.jaxb.Hit;
import com.supareno.pgnparser.jaxb.Hits;

/**
 * The {@code TestHits} class is used to test the {@link Hits} class.
 * 
 * @author reno
 * @version 1.1
 */
public class TestHits {

	private Hits hits1 = null;
	private Hits hits2 = null;

	@Before
	public void setUpMethod(){
		hits1 = new Hits();
		hits2 = new Hits();
	}

	/**
	 * Tests the Hits equality without Hit.
	 */
	@Test
	public void testEqualityWithoutHit(){
		assertEquals(hits1, hits2);
		assertEquals(hits2, hits1);
		assertEquals(hits2, hits2);
		assertEquals(hits1, hits1);

		assertEquals(hits1.getHit().size(), 0);
		assertEquals(hits2.getHit().size(), 0);
	}

	/**
	 * Tests the Hits equality with Hit.
	 */
	@Test
	public void testEqualityWithHit(){
		Hit hit1 = new Hit();
		hit1.setNumber("1");
		hit1.setContent("e4 e5");
		Hit hit2 = new Hit();
		hit2.setNumber("2");
		hit2.setContent("Nf3 Nf6");

		hits1.getHit().add(hit1);
		hits1.getHit().add(hit2);
		hits2.getHit().add(hit1);
		hits2.getHit().add(hit2);

		assertEquals(hits1, hits2);
		assertEquals(hits2, hits1);
		assertEquals(hits2, hits2);
		assertEquals(hits1, hits1);

		assertEquals(hits1.getHit().size(), 2);
		assertEquals(hits2.getHit().size(), 2);
	}

	/**
	 * Tests the Hits difference with different Hit.
	 */
	@Test
	public void testNotSameWithDifferentHit(){
		Hit hit1 = new Hit();
		hit1.setNumber("1");
		hit1.setContent("e4 e5");
		Hit hit2 = new Hit();
		hit2.setNumber("2");
		hit2.setContent("Nf3 Nf6");

		hits1.getHit().add(hit1);
		hits1.getHit().add(hit2);
		hits2.getHit().add(hit1);

		assertNotSame(hits1, hits2);
		assertNotSame(hits2, hits1);

		assertEquals(hits1.getHit().size(), 2);
		assertEquals(hits2.getHit().size(), 1);
	}

	@After
	public void cleanUpMethod(){
		hits1 = null;
		hits2 = null;
	}
}
