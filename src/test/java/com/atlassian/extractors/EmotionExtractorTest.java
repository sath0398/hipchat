package com.atlassian.extractors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import static org.junit.Assert.assertEquals;



@RunWith(JUnit4.class)
public class EmotionExtractorTest {


    @Test
    public void happy_path_multiple_match(){
        String input = "Good morning! (megusta) (coffee)";
        final EmotionExtractor emotionExtractor = new EmotionExtractor();
        List<String> matchedStrings =  emotionExtractor.parseEmotions(input);
        assertEquals(2, matchedStrings.size());

    }

    @Test
    public void no_match(){
        String input = "Good morning! ";
        final EmotionExtractor emotionExtractor = new EmotionExtractor();
        List<String> matchedStrings =  emotionExtractor.parseEmotions(input);
        assertEquals(0, matchedStrings.size());

    }


}
