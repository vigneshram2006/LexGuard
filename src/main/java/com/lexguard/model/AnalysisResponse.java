package com.lexguard.model;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResponse {

    private String priority;
    private String summary;
    private List<Finding> findings = new ArrayList<>();
    private List<String> rights = new ArrayList<>();
    private List<String> hotspots = new ArrayList<>();
    private List<String> suggestedActions = new ArrayList<>();

    public AnalysisResponse() {}

    private AnalysisResponse(Builder b) {
        this.priority = b.priority;
        this.summary = b.summary;
        this.findings = b.findings;
        this.rights = b.rights;
        this.hotspots = b.hotspots;
        this.suggestedActions = b.suggestedActions;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String priority;
        private String summary;
        private List<Finding> findings = new ArrayList<>();
        private List<String> rights = new ArrayList<>();
        private List<String> hotspots = new ArrayList<>();
        private List<String> suggestedActions = new ArrayList<>();

        public Builder priority(String priority) { this.priority = priority; return this; }
        public Builder summary(String summary) { this.summary = summary; return this; }
        public Builder findings(List<Finding> findings) { this.findings = findings; return this; }
        public Builder rights(List<String> rights) { this.rights = rights; return this; }
        public Builder hotspots(List<String> hotspots) { this.hotspots = hotspots; return this; }
        public Builder suggestedActions(List<String> suggestedActions) { this.suggestedActions = suggestedActions; return this; }
        public AnalysisResponse build() { return new AnalysisResponse(this); }
    }

    public String getPriority() { return priority; }
    public String getSummary() { return summary; }
    public List<Finding> getFindings() { return findings; }
    public List<String> getRights() { return rights; }
    public List<String> getHotspots() { return hotspots; }
    public List<String> getSuggestedActions() { return suggestedActions; }

    public void setPriority(String priority) { this.priority = priority; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setFindings(List<Finding> findings) { this.findings = findings; }
    public void setRights(List<String> rights) { this.rights = rights; }
    public void setHotspots(List<String> hotspots) { this.hotspots = hotspots; }
    public void setSuggestedActions(List<String> suggestedActions) { this.suggestedActions = suggestedActions; }
}
