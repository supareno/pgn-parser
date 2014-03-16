/*
 * Loggable.java
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
package com.supareno.pgnparser;

import org.apache.log4j.Level;

/**
 * @author supareno
 */
public interface Loggable {

  /**
   * Initializes the Log4j file.
   * 
   * @param file the Log4j file.
   */
  void setLoggerConfiguratorFile ( String file );

  /**
   * Returns the Log4j level.
   * 
   * @return the Log4j level.
   */
  Level getLoggerLevel ();

  /**
   * Sets the Log4j level.
   * 
   * @param level the Log4j level.
   */
  void setLoggerLevel ( Level level );

  /**
   * Logs the message using the Logger with the Level set.
   * 
   * @param msg the message to lo;
   */
  void log ( String msg );

  /**
   * Logs the message and the Throwable using the Logger with the Level set
   * in the constructor.
   * 
   * @param msg the message to log
   * @param t the Throwable to log
   */
  void log ( String msg, Throwable t );
}
