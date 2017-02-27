package com.atlassian.visitor;

import com.atlassian.extractors.EmotionExtractor;
import com.atlassian.extractors.MentionExtractor;
import com.atlassian.extractors.UrlExtractor;

/**
 * Created by satheish on 2/19/17.
 */
public interface Visitor {

    void visit(EmotionExtractor emotionExtractor);
    void visit(MentionExtractor mentionExtractor);
    void visit(UrlExtractor urlExtractor);

}
