package com.lexguard.model;

import java.util.List;

public record Scenario(String code, String category, List<String> keywords, String guidance) {}
