package com.atlassian.resource;

import com.atlassian.model.ChatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by satheish on 2/26/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;




    @Test
    public void with_emotions() throws Exception {
        String input = "Good morning! (megusta) (coffee)";

        String response = restTemplate.postForObject("/atlassian/v1/chat", ((Object) input), String.class);
        assertNotNull(response);

        ObjectMapper objectMapper = new ObjectMapper();

        ChatResponse chatResponse = objectMapper.readValue(response, ChatResponse.class);
        assertEquals(chatResponse.getEmotions().size(), 2);

    }

    @Test
    public void with_mentions() throws Exception {
        String input = "@chris you around?";

        String response = restTemplate.postForObject("/atlassian/v1/chat", ((Object) input), String.class);
        assertNotNull(response);

        ObjectMapper objectMapper = new ObjectMapper();

        ChatResponse chatResponse = objectMapper.readValue(response, ChatResponse.class);
        assertEquals(chatResponse.getMentions().size(), 1);

    }


    @Test
    public void with_links() throws Exception {
        String input = "Olympics are starting soon; http://www.nbcolympics.com";

        String response = restTemplate.postForObject("/atlassian/v1/chat", ((Object) input), String.class);
        assertNotNull(response);

        ObjectMapper objectMapper = new ObjectMapper();

        ChatResponse chatResponse = objectMapper.readValue(response, ChatResponse.class);
        assertEquals(chatResponse.getLinks().size(), 1);


    }

    @Test
    public void with_all() throws Exception {
        String input = "Good morning! (megusta) (coffee) " +
                "@chris you around?" +
                "Olympics are starting soon; http://www.nbcolympics.com";

        String response = restTemplate.postForObject("/atlassian/v1/chat", ((Object) input), String.class);
        assertNotNull(response);

        ObjectMapper objectMapper = new ObjectMapper();

        ChatResponse chatResponse = objectMapper.readValue(response, ChatResponse.class);
        assertEquals(chatResponse.getMentions().size(), 1);
        assertEquals(chatResponse.getEmotions().size(), 2);
        assertEquals(chatResponse.getLinks().size(), 1);

    }


}
