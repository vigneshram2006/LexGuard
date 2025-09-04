package com.lexguard.model;

public class Finding {
    private String code;
    private String category;
    private String guidance;

    public Finding() {}

    public Finding(String code, String category, String guidance) {
        this.code = code;
        this.category = category;
        this.guidance = guidance;
    }

    public String getCode() { return code; }
    public String getCategory() { return category; }
    public String getGuidance() { return guidance; }

    public void setCode(String code) { this.code = code; }
    public void setCategory(String category) { this.category = category; }
    public void setGuidance(String guidance) { this.guidance = guidance; }
}
