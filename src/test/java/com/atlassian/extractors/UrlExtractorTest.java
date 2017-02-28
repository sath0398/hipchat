package com.atlassian.extractors;

import com.atlassian.model.Links;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ws.rs.core.Link;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class UrlExtractorTest {
    @Test
    public void happy_path_one_match() throws Exception{
        String input = "Olympics are starting soon; http://www.nbcolympics.com";
        final UrlExtractor urlExtractor = new UrlExtractor();
        List<Links> matchedStrings =  urlExtractor.parseLinks(input);
        assertEquals(1, matchedStrings.size());

    }

    @Test
    public void happy_path_no_match() throws Exception{
        String input = "you around?";
        final UrlExtractor urlExtractor = new UrlExtractor();
        List<Links> matchedStrings =  urlExtractor.parseLinks(input);
        assertEquals(0, matchedStrings.size());

    }
}
