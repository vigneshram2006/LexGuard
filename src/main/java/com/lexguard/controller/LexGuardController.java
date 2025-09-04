package com.lexguard;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class LexGuardController {

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> request) {
        String scenario = request.get("scenario");

        Map<String, Object> response = new HashMap<>();

        if (scenario != null && scenario.toLowerCase().contains("evicted")) {
            response.put("priority", "High");
            response.put("rights", Arrays.asList("Right to fair hearing", "Right to shelter"));
            response.put("hotspots", Arrays.asList("Tenant law", "Eviction rules"));
            response.put("suggestedActions", Arrays.asList("Consult a lawyer", "File a stay order"));
        } else {
            response.put("priority", "Medium");
            response.put("rights", Arrays.asList("Basic constitutional rights"));
            response.put("hotspots", Arrays.asList("General law"));
            response.put("suggestedActions", Arrays.asList("Consult legal aid"));
        }

        return response;
    }
}
