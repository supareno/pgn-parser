# pgn-parser


The pgn-parser library is a java parser for pgn files (https://en.wikipedia.org/wiki/Portable_Game_Notation).
It can parse pgn, xml and json files to java objects and create pgn, json and xml files from java objects.

This parser respects the official pgn file definition but does not implement (yet) all the pgn 
notation.

* The xml file and its definition is not official as the pgn notation. 
* The json file is not official as the pgn notation. 

These formats are made by me.

## Building

### Building PGNParser with Maven


**IMPORTANT: This build uses a jdk 1.8 minimum**

PGNParser is built with Maven 3. If you use an old maven version, build phase will stop with a warning. 

     mvn clean package 

A checkstyle analysis is done at every build to check the code produced.

### Assembling PGNParser

If you want to bundle the source of the PGNParser, you could run the following command:

     mvn assembly:single

## Installing PGNParser 

### With Maven from the sources

First, clone the repository from Github. And after, add the lib to your _.m2_ repository or your artifact repository.

#### Maven command line style

    mvn clean install

or, if you set your credentials and url artifact repository in your _settings.xml_ in your local _.m2_ folder, you
can run the following command:

    mvn clean deploy 

### To your Artifact repository from a jar

Download a release from Github (tar, gzip or zip), unarchive it where you want and go to your artifact repository home 
page, login and add the dependency to your RELEASE local repository or another one with the following properties:

 * groupId      : org.supareno.pgnparser
 * artifactId   : pgnparser
 * version      : 3.0.0
 * packaging    : jar

Specify the jar to download and submit the form.

### Use PGNParser as dependency in your project

#### Maven style

```xml
<dependency>
    <groupId>org.supareno.pgnparser</groupId>
    <artifactId>pgnparser</artifactId>
    <version>3.0.0</version>
</dependency>
```

#### Gradle style

    compile: 'org.supareno.pgnparser:pgnparser:3.0.0'

## Using PGNParser
  
### Parsing files

Here is an sample code for parsing pgn, xml and json files to Java objects.

```java
public class MainClass {

  public static void main ( String[] args ) {

    // to parse pgn files
    Parser parser = new PGNParser();
    Games games = parser.parseFile("path_to_you_pgn_file.pgn");
    System.out.println(games.getGame ().size () + " for pgn parsing");

    // to parse xml pgn files
    Parser jaxbParser = new JAXBPGNParser();
    Games gamesFromJaxbParser = jaxbParser.parseFile("path_to_you_xml_pgn_file.xml");
    System.out.println(gamesFromJaxbParser.getGame().size() + " for xml parsing");
    
    // to parse json pgn files
    Parser jsonParser = new JSONPGNParser();
    Games gamesFromJsonParser = jsonParser.parseFile("path_to_you_json_pgn_file.json");
    System.out.println(gamesFromJsonParser.getGame().size() + " for json parsing");
  }
}
```

## PGN files

The source code contains pgn, xml and json files that can be use as example or as source for your programs.


## What else

In case of problems send an e-mail note to

supareno@gmail.com

Have fun !

copyright(c) 2008-2018 supareno and Contributors.