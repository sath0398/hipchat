package com.atlassian.config;

import com.atlassian.resource.ChatResource;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by satheish on 2/18/17.
 */
@Component
@ApplicationPath("/atlassian/v1")
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;


    @PostConstruct
    public void init() {
        // Register components where DI is needed
        register(ChatResource.class);
        this.configureSwagger();
    }

    @Autowired
    public JerseyConfig(ObjectMapper objectMapper) {
        // register endpoints
        packages("com.atlassian");
        // register jackson for json
        register(new ObjectMapperContextResolver(objectMapper));
    }

    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper mapper;


        public ObjectMapperContextResolver(ObjectMapper mapper) {
            this.mapper = mapper;
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }

    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        this.register(ChatResource.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("atlassian chat");
        config.setTitle("Chat application");
        config.setVersion("v1");
        config.setContact("Satheish Kumar C");
        config.setSchemes(new String[]{"http"});
        config.setHost("localhost:8080");
        config.setBasePath("/api");
        config.setResourcePackage("com.atlassian.resource;");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}