/*
 * JsonPGNWriter.java
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
package com.supareno.pgnparser.json;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import com.supareno.pgnparser.AbstractPGNWriter;
import com.supareno.pgnparser.PGNType;
import com.supareno.pgnparser.jaxb.Games;

/**
 * Uses JAXB with Jettison to produce JSON.
 * 
 * @author supareno
 */
public class JsonPGNWriter extends AbstractPGNWriter {

  @Override
  public boolean writePGNGames ( Games games ) throws IllegalArgumentException {
    JAXBContext jc;
    boolean ok = true;
    BufferedWriter out = null;
    try {
      jc = JAXBContext.newInstance ( Games.class );

      Configuration config = new Configuration ();
      MappedNamespaceConvention con = new MappedNamespaceConvention ( config );
      out = new BufferedWriter ( new FileWriter ( getFullFileName () ) );
      XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter ( con , out );

      Marshaller marshaller = jc.createMarshaller ();
      marshaller.marshal ( games , xmlStreamWriter );
    } catch ( Exception e ) {
      ok = false;
    } finally {
      try {
        out.close ();
      } catch ( IOException e ) {
        ok = false;
        log ( "cannot close the writer !" );
      }
    }
    return ok;
  }

  @Override
  public String getExtension () {
    return PGNType.JSON.getExtension ();
  }

}
