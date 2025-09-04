package com.lexguard.service;

import com.lexguard.model.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RuleEngine {

    private final List<Scenario> scenarios = new ArrayList<>();

    public RuleEngine() {
        // Define common legal scenarios with keywords and mock guidance
        scenarios.add(new Scenario("evicted", "Tenant/Eviction",
                List.of("evict", "evicted", "eviction", "landlord"),
                "🏠 Tenant Rights: In many places, you are entitled to written notice and a hearing before eviction."));
        scenarios.add(new Scenario("fired", "Employment/Termination",
                List.of("fired", "terminated", "layoff", "laid off", "dismissed"),
                "💼 Employment Rights: You may be entitled to severance or notice depending on local law and contract."));
        scenarios.add(new Scenario("arrested", "Criminal/Arrest",
                List.of("arrest", "arrested", "custody", "police took me"),
                "🚓 Criminal Procedure: You typically have the right to remain silent and request a lawyer."));
        scenarios.add(new Scenario("harassment", "Harassment/Abuse",
                List.of("harassment", "harassed", "abuse", "stalking"),
                "🛡️ Protection: Document incidents and consider protective orders; contact local helplines."));
        scenarios.add(new Scenario("discriminated", "Discrimination",
                List.of("discriminate", "discriminated", "bias", "racist", "sexist"),
                "⚖️ Anti-Discrimination: File a complaint with the relevant commission/authority; keep records."));
        scenarios.add(new Scenario("scam", "Fraud/Scam",
                List.of("scam", "fraud", "phishing", "fake call"),
                "🔒 Fraud: Do not share OTPs; report to cybercrime portal and bank immediately."));
        scenarios.add(new Scenario("domestic", "Domestic Violence",
                List.of("domestic violence", "partner hit", "abusive partner"),
                "🏳️‍🌈 Safety First: Seek a safe place; contact emergency services and support NGOs."));
        scenarios.add(new Scenario("theft", "Theft",
                List.of("stolen", "theft", "robbed", "pickpocket"),
                "📝 Report theft promptly and keep receipts; track device IMEI if applicable."));
        scenarios.add(new Scenario("threat", "Threats/Extortion",
                List.of("threat", "extort", "blackmail"),
                "⚠️ Threats: Save evidence and report to authorities; avoid engaging further."));
        scenarios.add(new Scenario("cyberbully", "Cyberbullying",
                List.of("cyberbully", "bullying online", "trolled"),
                "🌐 Cyber Safety: Block & report accounts; capture screenshots as evidence."));
    }

    public List<Scenario> getSupportedScenarios() {
        return scenarios;
    }

    public AnalysisResponse evaluateTextOnly(String text) {
        return evaluate(new AnalysisRequest(text, null, null));
    }

    public AnalysisResponse evaluate(AnalysisRequest request) {
        String text = Optional.ofNullable(request.getText()).orElse("").trim();
        String lower = text.toLowerCase(Locale.ROOT);

        List<Finding> findings = new ArrayList<>();
        int priorityScore = 0;

        for (Scenario sc : scenarios) {
            for (String kw : sc.keywords()) {
                if (lower.contains(kw)) {
                    findings.add(new Finding(sc.code(), sc.category(), sc.guidance()));
                    // Prioritize certain categories
                    if (List.of("Criminal/Arrest", "Domestic Violence", "Threats/Extortion").contains(sc.category())) {
                        priorityScore += 3;
                    } else if (List.of("Fraud/Scam", "Harassment/Abuse").contains(sc.category())) {
                        priorityScore += 2;
                    } else {
                        priorityScore += 1;
                    }
                    break;
                }
            }
        }

        // Generic hints (crime hotspots & rights) – mock/demo data
        List<String> rights = new ArrayList<>();
        if (lower.contains("right") || findings.isEmpty()) {
            rights.add("You have the right to seek legal counsel and fair process.");
            rights.add("You can file a complaint with local authorities or relevant commission.");
        }

        List<String> hotspots = new ArrayList<>();
        if (lower.contains("crime") || findings.stream().anyMatch(f -> f.getCategory().toLowerCase().contains("criminal"))) {
            hotspots.add("Mock Hotspot: Central Market Area (high petty theft reports).");
            hotspots.add("Mock Hotspot: Station Road after 9pm (frequent phone snatching).");
        }

        // Edge cases
        if (text.isBlank()) {
            return AnalysisResponse.builder()
                    .priority("LOW")
                    .summary("Please provide details so we can help with guidance.")
                    .findings(List.of())
                    .rights(List.of("Right to safety and access to legal aid."))
                    .hotspots(List.of())
                    .suggestedActions(List.of("Describe your situation briefly.", "Avoid sharing personal IDs/OTPs."))
                    .build();
        }

        String priority = priorityScore >= 4 ? "HIGH" : (priorityScore >= 2 ? "MEDIUM" : "LOW");

        List<String> actions = new ArrayList<>();
        if (priority.equals("HIGH")) {
            actions.add("If in danger, contact emergency services immediately.");
        }
        actions.add("Document facts, dates, and any evidence (screenshots, receipts).");
        actions.add("Consider contacting a local lawyer/helpline for specific advice.");
        actions.add("This is a demo; verify guidance for your jurisdiction.");

        String summary;
        if (findings.isEmpty()) {
            summary = "No specific risk detected. General guidance provided.";
        } else {
            summary = "Detected scenarios: " + String.join(", ", findings.stream().map(Finding::getCategory).distinct().toList()) +
                    ". Priority: " + priority + ".";
        }

        return AnalysisResponse.builder()
                .priority(priority)
                .summary(summary)
                .findings(findings)
                .rights(rights)
                .hotspots(hotspots)
                .suggestedActions(actions)
                .build();
    }
}
