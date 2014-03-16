/*
 * AbstractPGNIO.java
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
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Abstract class for writing or parsing PGN files, urls, ...
 * 
 * @author supareno
 * 
 * @since 2.3.0
 */
public class AbstractPGNIO implements Loggable {

  // private Logger
  private static final Logger LOGGER = Logger
      .getLogger(AbstractPGNParser.class);

  /** Default Log4j logger Level: set to {@code DEBUG}. */
  public static final Level DEFAULT_LOGGER_LEVEL = Level.DEBUG;

  // Logger level
  private Level loggerLevel = DEFAULT_LOGGER_LEVEL;

  @Override
  public Level getLoggerLevel () {
    return this.loggerLevel;
  }

  @Override
  public void setLoggerLevel (Level lev) {
    this.loggerLevel = lev;
  }

  @Override
  public void setLoggerConfiguratorFile (String file) {
    if (file.length() > 0) {
      DOMConfigurator.configure(file);
    }
  }

  @Override
  public void log (String msg) {
    log(msg, null);
  }

  @Override
  public void log (String msg, Throwable t) {
    if (t != null) {
      LOGGER.log(loggerLevel, msg, t);
    } else {
      LOGGER.log(loggerLevel, msg);
    }
  }
}
