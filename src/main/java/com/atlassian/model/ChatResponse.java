package com.atlassian.model;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by satheish on 2/18/17.
 */
public class ChatResponse {

    private List<String> mentions = new ArrayList<>();
    private List<String> emotions = new ArrayList<>();
    private List<Links> links = new ArrayList<>();

    public List<String> getMentions() {
        return mentions;
    }

    public List<String> getEmotions() {
        return emotions;
    }

    public List<Links> getLinks() {
        return links;
    }


}

