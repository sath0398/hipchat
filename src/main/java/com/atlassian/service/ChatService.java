package com.atlassian.service;

import com.atlassian.extractors.EmotionExtractor;
import com.atlassian.extractors.Extractor;
import com.atlassian.extractors.MentionExtractor;
import com.atlassian.extractors.UrlExtractor;
import com.atlassian.model.ChatResponse;
import com.atlassian.visitor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by satheish on 2/18/17.
 */
@Service
@Scope("prototype")
public class ChatService implements Visitor {

    @Autowired
    private EmotionExtractor emotionExtractor;

    @Autowired
    private MentionExtractor mentionExtractor;

    @Autowired
    private UrlExtractor urlExtractor;


    private final ChatResponse chatResponse = new ChatResponse();

    private String inputToParse;


    public ChatResponse find(String inputToParse) {
        if (inputToParse == null || inputToParse.isEmpty()) {
            throw new RuntimeException("Input String to parse shouldn't be empty!");
        }
        this.inputToParse = inputToParse;
        List<Extractor> visitables = Arrays.asList(emotionExtractor, mentionExtractor, urlExtractor);

        for (Extractor extractor : visitables) {
            extractor.accept(this);
        }

        return chatResponse;
    }

    @Override
    public void visit(EmotionExtractor emotionExtractor) {
        chatResponse.getEmotions().addAll(emotionExtractor.parseEmotions(inputToParse));

    }

    @Override
    public void visit(MentionExtractor mentionExtractor) {
        chatResponse.getMentions().addAll(mentionExtractor.parseMentions(inputToParse));

    }

    @Override
    public void visit(UrlExtractor urlExtractor) {
        chatResponse.getLinks().addAll(urlExtractor.parseLinks(inputToParse));

    }


}