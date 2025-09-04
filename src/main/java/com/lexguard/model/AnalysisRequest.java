package com.lexguard.model;

public class AnalysisRequest {
    private String text;
    private String location;
    private String language;

    public AnalysisRequest() {}

    public AnalysisRequest(String text, String location, String language) {
        this.text = text;
        this.location = location;
        this.language = language;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
