/**
 * 
 */
package com.supareno.test.pgnparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.supareno.pgnparser.filters.PGNFileFilter;

/**
 * @author reno
 *
 */
public class TestPGNFilefilter {

	/**
	 * Test that the PGNFilefilter return true for a correct file name which is {@code filename.pgn}.
	 */
	@Test
	public void testFilterSucceed01() {
		File file = new File("filename.pgn");
		assertTrue("", new PGNFileFilter().accept(file));
	}

	/**
	 * Test that the PGNFilefilter return true for a correct file name which is {@code filename.pGn}.
	 */
	@Test
	public void testFilterSucceed02() {
		File file = new File("filename.pGn");
		assertTrue("", new PGNFileFilter().accept(file));
	}

	/**
	 * Test that the PGNFilefilter return true for a correct file name which is {@code filename.PGN}.
	 */
	@Test
	public void testFilterSucceed03() {
		File file = new File("filename.PGN");
		assertTrue("", new PGNFileFilter().accept(file));
	}


	/**
	 * Test that the PGNFilefilter return false for a bad file name which is {@code filename.pgn.Gn}.
	 */
	@Test
	public void testFilterFailed01 (){
		File file = new File("filename.pgn.Gn");
		assertFalse("", new PGNFileFilter().accept(file));
	}

	/**
	 * Test that the PGNFilefilter return false for a bad file name which is {@code filename.pGgn}.
	 */
	@Test
	public void testFilterFailed02 (){
		File file = new File("filename.pgGn");
		assertFalse("", new PGNFileFilter().accept(file));
	}

	/**
	 * Test that the PGNFilefilter return false for a bad file name which is {@code filename_pgn}.
	 */
	@Test
	public void testFilterFailed03 (){
		File file = new File("filename_pgn");
		assertFalse("", new PGNFileFilter().accept(file));
	}
	
	/**
	 * Test that the PGNFilefilter return false for a bad file name which is {@code filename_pgn.x}.
	 */
	@Test
	public void testFilterFailedNoExtension (){
		File file = new File("filename_pgn.x");
		assertFalse("", new PGNFileFilter().accept(file));
	}
	
	/**
	 * Test that the PGNFilefilter return false for a bad file name which is {@code filename_pgn}.
	 */

	@Test
	public void testFilterFailedWithNull (){
		assertFalse("", new PGNFileFilter().accept(null));
	}
}
