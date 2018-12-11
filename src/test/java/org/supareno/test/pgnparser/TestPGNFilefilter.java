/**
 * 
 */
package org.supareno.test.pgnparser;


import java.io.File;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.filters.PGNFileFilter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author reno
 *
 */
public class TestPGNFilefilter {


	@Test
	@DisplayName("Filter a filename on '.pgn' extension returns true")
	void filter_succeed_with_pgn() {
		File file = new File("filename.pgn");
		assertThat(new PGNFileFilter().accept(file)).isTrue();
	}

	@Test
	@DisplayName("Filter a filename on '.pGn' extension returns true")
	void filter_succeed_with_pGn() {
		File file = new File("filename.pGn");
		assertThat(new PGNFileFilter().accept(file)).isTrue();
	}

	@Test
	@DisplayName("Filter a filename on '.pGn' extension returns true")
	void filter_succeed_with_PGN() {
		File file = new File("filename.pGn");
		assertThat(new PGNFileFilter().accept(file)).isTrue();
	}

	@Test
	@DisplayName("Filter a filename on '.pgn.gn' extension returns false")
	void filter_failed_with_pgngn (){
		File file = new File("filename.pgn.Gn");
		assertThat(new PGNFileFilter().accept(file)).isFalse();
	}

	@Test
	@DisplayName("Filter a filename on '.pgGn' extension returns false")
	void filter_failed_with_pgGn (){
		File file = new File("filename.pgGn");
		assertThat(new PGNFileFilter().accept(file)).isFalse();
	}


	@Test
	@DisplayName("Filter a filename on '_pgn' extension returns false")
	void filter_failed_with_underscore_pgn (){
		File file = new File("filename_pgn");
		assertThat(new PGNFileFilter().accept(file)).isFalse();
	}


	@Test
	@DisplayName("Filter a filename on '.pgnx' extension returns false")
	void filter_failed_with_underscore_pgnx (){
		File file = new File("filename.pgnx");
		assertThat(new PGNFileFilter().accept(file)).isFalse();
	}

	@Test
	@DisplayName("Filter a filename on '_pgn' extension returns false")
	void filter_failed_with_null (){
		assertThat(new PGNFileFilter().accept(null)).isFalse();
	}
}
