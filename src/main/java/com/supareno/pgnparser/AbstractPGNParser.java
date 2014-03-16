/*
 * AbstractPGNParser.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import com.supareno.pgnparser.jaxb.Game;
import com.supareno.pgnparser.jaxb.Games;
import com.supareno.pgnparser.utils.PGNParserUtils;

/**
 * The {@code AbstractPGNParser} class is the abstract implementation of the
 * {@link Parser} interface. It defines few methods used by the parsers.
 * 
 * @author supareno
 * 
 * @version 2.3.0
 * 
 * @since 1.0
 */
public abstract class AbstractPGNParser extends AbstractPGNIO implements Parser {

  @Override
  public Games parseFile (String file) {
    return parseFile(new File(file));
  }

  @Override
  public Games parseFile (File file) {
    Reader r = null;
    try {
      r = new FileReader(file);
    } catch (FileNotFoundException e) {
      log("error in parseFile(File) ", e);
    }
    return parseFile(r);
  }

  @Override
  public Games parseURL (String url) {
    URL urlTmp = null;
    try {
      urlTmp = new URL(url);
    } catch (MalformedURLException e) {
      log("error in parseURL(String) ", e);
    }
    return parseURL(urlTmp);
  }

  @Override
  public Games parseURL (URL url) {
    Games games = null;
    try {
      games = parseFile(new InputStreamReader(url.openStream()));
    } catch (IOException e) {
      log("error in parseURL(URL) ", e);
    }
    return games;
  }

  /**
   * Sets the Game attribute value according to the attribute.
   * <p>
   * This method uses reflection to build the method according to the attribute
   * only if the attribute is registered as a valid attribute in the
   * {@link PGNParserConstants#ATTRIBUTES_MAP}.
   * </p>
   * <p>
   * If {@code attrValue} is not value, the value set is
   * {@link PGNParserUtils#QUESTION_MARK}
   * </p>
   * 
   * @param pgnGame the current Game.
   * @param attribute the Game attribute.
   * @param attrValue the attribute value.
   * 
   * @see #setPGNGameAttributeAndValue(Game, String, String, String)
   */
  public void setPGNGameAttributeAndValue (Game pgnGame, String attribute,
      String attrValue) {
    setPGNGameAttributeAndValue(pgnGame, attribute, attrValue,
        PGNParserUtils.QUESTION_MARK);
  }

  /**
   * Sets the Game attribute value according to the attribute.
   * <p>
   * This method uses reflection to build the method according to the attribute
   * only if the attribute is registered as a valid attribute in the
   * {@link PGNParserConstants#ATTRIBUTES_MAP}.
   * </p>
   * 
   * @param pgnGame the current Game.
   * @param attribute the Game attribute.
   * @param attrValue the attribute value.
   * @param replacementValue the value to use if the attribute's value is not
   *          valid
   */
  public void setPGNGameAttributeAndValue (Game pgnGame, String attribute,
      String attrValue, String replacementValue) {
    if (PGNParserConstants.ATTRIBUTES_MAP.containsKey(attribute)) {
      Method method = null;
      try {
        method = pgnGame.getClass().getMethod(
            PGNParserConstants.ATTRIBUTES_MAP.get(attribute), String.class);
        method.invoke(pgnGame,
            PGNParserUtils.isValidString(attrValue) ? attrValue
                : replacementValue);
      } catch (SecurityException e) {
        log("SecurityException on built method : " + method, e);
      } catch (NoSuchMethodException e) {
        log("NoSuchMethodException on built method : " + method, e);
      } catch (IllegalArgumentException e) {
        log("IllegalArgumentException on built method : " + method, e);
      } catch (IllegalAccessException e) {
        log("IllegalAccessException on built method : " + method, e);
      } catch (InvocationTargetException e) {
        log("InvocationTargetException on built method : " + method, e);
      }
    }
  }
}
