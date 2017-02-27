package com.atlassian.extractors;

import com.atlassian.visitor.Visitor;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

/**
 * Created by satheish on 2/19/17.
 */

@Service
public class UrlExtractor extends Extractor {

    private static final Pattern PATTERN =
            Pattern.compile("\\b((https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])");

    private static final Client client = Client.create();


    @Override
    public List<Link> parseLinks(String input) {
        List<Link> links = new ArrayList<>();
        String[] result = input.split("\\s");
        List<String> links_temp_list = Arrays.stream(result)
                .filter(s -> PATTERN.matcher(s).matches())
                .collect(Collectors.toList());

        if(links_temp_list == null || links_temp_list.isEmpty()){
            return links;
        }

        for(String link: links_temp_list){
            links.add(Link
                    .fromUri(link)
                    .title(getTitle(link))
                    .build());
        }
        return links;
    }

    private static String getTitle(String link){
        WebResource webResource = client.resource(link);
        Document doc = Jsoup.parse(webResource.get(String.class));
        return doc.title();
    }


    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

}
