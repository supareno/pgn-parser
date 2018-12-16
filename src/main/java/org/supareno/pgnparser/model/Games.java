package org.supareno.pgnparser.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Games {

    @JacksonXmlElementWrapper(useWrapping = false)
    protected List<Game> games = new ArrayList<>();
}
