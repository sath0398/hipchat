package com.atlassian.model;

public class Links {

    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Links(){

    }

    public Links(String url, String title){
        this.url = url;
        this.title = title;
    }
}
