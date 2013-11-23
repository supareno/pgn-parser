/*
 * PGNParserUtils.java.java 
 */
package com.supareno.pgnparser.utils;

/**
 * This class is a utility class used.
 * @author reno
 * @since 2.2
 */
public final class PGNParserUtils {

	/* @throws UnsupportedOperationException when this constructor is called.  */
	private PGNParserUtils(){
		throw new UnsupportedOperationException("Cannot be instantiate.");
	}
	
	/**
	 * Return {@code true} if {@code str} is not null and if its length is superior to {@code 0}.
	 * Otherwise returns {@code false}.
	 * 
	 * @param str the string to check.
	 * 
	 * @return {@code true} if {@code str} is not null and if its length is superior to {@code 0}.
	 * Otherwise returns {@code false}.
	 */
	public static boolean isValidString( String str ) {
		return str != null && str.length() > 0;
	}
}
