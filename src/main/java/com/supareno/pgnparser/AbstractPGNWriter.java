/*
 * AbstractPGNWriter.java
 * 
 * Copyright 2008-2011 supareno
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.supareno.pgnparser;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * The {@code AbstractPGNWriter} abstract class defines some methods used by all the writers.
 * @author reno
 * @version 1.0
 */
public abstract class AbstractPGNWriter implements Writer {

	/**
	 * The {@code LOGGER} is the Log4j Logger used by the Writer.
	 */
	protected static final Logger LOGGER = Logger.getLogger(AbstractPGNWriter.class);

	/**
	 * The {@code DEFAULT_LOGGER_LEVEL} Level is used by default by Log4j if no Level is set: the
	 * value is set to {@code DEBUG}.
	 */
	protected static final Level DEFAULT_LOGGER_LEVEL = Level.DEBUG;

	/**
	 * The {@code DATEFORMAT} SimpleDateFormat is used to format date file name: the value
	 * is set to {@literal yyyMMddHHmmss}.
	 */
	public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	// name of the file
	private String filename = DEFAULT_FILE_NAME;
	// Logger level
	private Level loggerLevel = DEFAULT_LOGGER_LEVEL;

	/**
	 * Default file name: sets to {@code pgnfile} with the date of the
	 * creation added at the end. The format of the date is:<p>{@code yyyyMMddHHmmss}</p>
	 * The extension will be added automatically by the writer.
	 */
	public static final String DEFAULT_FILE_NAME="pgnfile";

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#setLoggerLevel(org.apache.log4j.Level)
	 */
	public void setLoggerLevel(Level level){ this.loggerLevel = level; }

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#getLoggerLevel()
	 */
	public Level getLoggerLevel() { return this.loggerLevel; }

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#getFileName()
	 */
	public String getFileName() { return this.filename; }

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#setFileName(java.lang.String)
	 */
	public void setFileName(String filename) {
		String fname=filename;
		if(filename.indexOf(".")>0){
			fname=filename.substring(0, filename.indexOf("."));
		}
		this.filename=fname;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#setLoggerConfiguratorFile(java.lang.String)
	 */
	public void setLoggerConfiguratorFile(String file) {
		if(file.length()>0){
			DOMConfigurator.configure(file);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#log(java.lang.String)
	 */
	public void log(String msg){ log(msg, null); }

	/*
	 * (non-Javadoc)
	 * @see com.supareno.pgnparser.Writer#log(java.lang.String, java.lang.Throwable)
	 */
	public void log(String msg, Throwable t){
		if(LOGGER.isEnabledFor(loggerLevel)){
			if(t!=null){
				LOGGER.log(loggerLevel, msg, t);
			}else{
				LOGGER.log(loggerLevel, msg);
			}
		}
	}

	/**
	 * Formats a Date and returns it to a String representation using the
	 * {@link #DATEFORMAT} SimpleDateFormat pattern. A new Date is used at
	 * every call.
	 * 
	 * @return a String representation of a Date using the {@link #DATEFORMAT}
	 * SimpleDateFormat pattern.
	 * 
	 * @see #formatDate(SimpleDateFormat)
	 */
	protected static synchronized String formatDate(){
		return formatDate(DATEFORMAT);
	}

	/**
	 * Formats a Date and returns it to a String representation using the
	 * {@code sdf} SimpleDateFormat pattern. A new Date is used at
	 * every call.
	 * 
	 * @param sdf the SimpleDateFormat used for the formatting
	 * 
	 * @return a String representation of a Date using the {@code format} pattern
	 */
	protected static synchronized String formatDate(SimpleDateFormat sdf){
		return sdf.format(new Date());
	}
}
