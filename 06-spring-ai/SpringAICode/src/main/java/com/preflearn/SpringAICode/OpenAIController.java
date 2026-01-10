package com.preflearn.SpringAICode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class OpenAIController {

    private ChatClient chatClient;

    /* public OpenAIController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    } */

    public OpenAIController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/{message}")
    public String getAnswer(@PathVariable String message) {

        // String response = chatClient.call(message);
        ChatResponse chatResponse = chatClient.prompt(message).call().chatResponse();
        assert chatResponse != null;
        String response = chatResponse.getResult().getOutput().getText();
        System.out.println("Model: " + chatResponse.getMetadata().getModel());
        System.out.println("Rate limit: " + chatResponse.getMetadata().getRateLimit());
        System.out.println("Usage: " + chatResponse.getMetadata().getUsage());

        return response;
    }
}
