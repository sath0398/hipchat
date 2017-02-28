package com.atlassian.extractors;

import com.atlassian.model.Links;
import com.atlassian.visitor.Visitable;

import javax.ws.rs.core.Link;
import java.net.MalformedURLException;
import java.util.List;

public abstract class Extractor implements Visitable {

    // Dummy Impl, subclass must override to implement the functionality
    public List<String> parseEmotions(String input){
        return null;
    }

    public List<String> parseMentions(String input){
        return null;
    }

    public List<Links> parseLinks(String input) throws MalformedURLException {
        return null;
    }
}
