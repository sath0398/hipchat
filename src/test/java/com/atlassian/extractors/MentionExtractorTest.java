package com.atlassian.extractors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MentionExtractorTest {

    @Test
    public void happy_path_one_match(){
        String input = "@chris you around?";
        final MentionExtractor mentionExtractor = new MentionExtractor();
        List<String> matchedStrings =  mentionExtractor.parseMentions(input);
        assertEquals(1, matchedStrings.size());

    }

    @Test
    public void happy_path_no_match(){
        String input = "you around?";
        final MentionExtractor mentionExtractor = new MentionExtractor();
        List<String> matchedStrings =  mentionExtractor.parseMentions(input);
        assertEquals(0, matchedStrings.size());

    }
}
