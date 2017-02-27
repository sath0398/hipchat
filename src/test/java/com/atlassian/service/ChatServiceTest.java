package com.atlassian.service;

import com.atlassian.model.ChatResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by satheish on 2/26/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Test
    public void chat_with_only_emotions(){
        String input = "Good morning! (megusta) (coffee)";
        ChatResponse chatResponse = chatService.find(input);
        assertEquals(2, chatResponse.getEmotions().size());

    }

    @Test
    public void chat_with_only_mentions(){
        String input = "@chris you around?";
        ChatResponse chatResponse = chatService.find(input);
        assertEquals(1, chatResponse.getMentions().size());

    }

    @Test
    public void chat_with_only_Links(){
        String input = "Olympics are starting soon; http://www.nbcolympics.com";
        ChatResponse chatResponse = chatService.find(input);
        assertEquals(1, chatResponse.getLinks().size());
        assertNotNull(chatResponse.getLinks().get(0).getTitle());
        assertNotNull(chatResponse.getLinks().get(0).getUri());


    }

    @Test
    public void chat_with_all_Links(){
        String input = "Good morning! (megusta) (coffee) " +
                "@chris you around?" +
                "Olympics are starting soon; http://www.nbcolympics.com";

        ChatResponse chatResponse = chatService.find(input);
        assertEquals(1, chatResponse.getLinks().size());
        assertNotNull(chatResponse.getLinks().get(0).getTitle());
        assertNotNull(chatResponse.getLinks().get(0).getUri());

        assertEquals(2, chatResponse.getEmotions().size());
        assertEquals(1, chatResponse.getMentions().size());


    }
}
