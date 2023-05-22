package com.datvutech.educationportalws.openai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datvutech.educationportalws.openai.model.request.ChatRequest;
import com.datvutech.educationportalws.openai.service.ChatGptService;

@RestController
@RequestMapping("v1/openai/chatgpt")
public class ChatGptController {
    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("answer")
    public ResponseEntity<String> getAnswer(@RequestBody ChatRequest chatReq) {
        String response = chatGptService.getAnswer(chatReq);
        return ResponseEntity.ok(response);
    }
}
