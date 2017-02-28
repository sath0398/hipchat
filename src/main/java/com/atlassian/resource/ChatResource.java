package com.atlassian.resource;

import com.atlassian.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by satheish on 2/18/17.
 */
@Component
@Path("/chat")
public class ChatResource {

    @Autowired
    private ChatService chatService;

    @POST
    @Consumes("text/plain")
    @Produces(MediaType.APPLICATION_JSON)
    public Response chat(String input) {
        Response chatResponse = Response
                .status(200)
                .entity(chatService.find(input)).build();
        return chatResponse;
    }
}