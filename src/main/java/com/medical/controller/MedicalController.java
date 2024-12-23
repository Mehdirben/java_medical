package com.medical.controller;

import com.medical.service.ChatbotService;
import com.medical.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MedicalController {
    
    @Autowired
    private ChatbotService chatbotService;
    
    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping(value = "/chat", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> chat(@RequestBody String message) {
        try {
            String response = chatbotService.processQuery(message);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                .body("Error processing your request: " + e.getMessage());
        }
    }

    @PostMapping(value = "/diagnose", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> diagnose(@RequestBody String symptoms) {
        try {
            String result = diagnosisService.diagnose(symptoms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                .body("Error processing diagnosis: " + e.getMessage());
        }
    }
}
