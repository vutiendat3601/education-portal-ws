package com.datvutech.educationportalws.openai.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.datvutech.educationportalws.openai.model.request.ChatRequest;

@Service
@PropertySource("classpath:app-configs.properties")
public class ChatGptService {

    @Value("${app.openai.chatgpt.api_url}")
    private String apiUrl;

    @Value("${app.openai.chatgpt.api_token}")
    private String apiToken;

    @Value("${app.openai.chatgpt.model}")
    private String model;

    private final String REQUEST_BODY = """
                {
                    "model": "%s",
                    "messages": [{"role": "user", "content": %s}],
                    "temperature": 0.7
                }
            """;

    public String getAnswer(ChatRequest chatReq) {
        try {
            String reqBody = REQUEST_BODY.formatted(model, chatReq.message());

            System.out.println(reqBody);

            HttpRequest request = HttpRequest.newBuilder(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiToken)
                    .POST(BodyPublishers.ofString(reqBody))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException("Server error! Please try again!");
        } catch (InterruptedException e) {
            throw new RuntimeException("Server error! Please try again!");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String temp = """
                    {
                        "model": "gpt-3.5-turbo",
                        "messages": [{"role": "user", "content": "hi"}],
                        "temperature": 0.7
                    }
                """;
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + "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")
                .POST(BodyPublishers.ofString(temp))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
