/*
 * PGNType.java
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
package com.supareno.pgnparser;

/**
 * The {@code PGNType} enumaration defines the differnets types of png files, writers
 * and / or parsers.
 * <p>
 * Two types are defined:
 * <ul>
 * 	<li>XML</li>
 * 	<li>PGN</li>
 * </ul>
 * </p>
 * @author reno
 * @version 1.0
 */
public enum PGNType {

	/**
	 * The {@code XML} type defines an xml PGN file, parser or writer. The extension assoxiated
	 * to this type is {@code xml}.
	 */
	XML("xml"),
	/**
	 * The {@code PGN} type defines a pgn file, parser or writer. The extension assoxiated
	 * to this type is {@code pgn}.
	 */
	PGN("pgn");

	private final String extension;
	PGNType(String ext){
		this.extension = ext;
	}

	/**
	 * Returns the extension associated to this type.
	 * @return the extension associated to this type.
	 */
	public String getExtension(){ return this.extension; }
}
