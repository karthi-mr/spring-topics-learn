package com.preflearn.SpringAICode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api")
public class OpenAIController {

    private ChatClient chatClient;
    private ChatMemory chatMemory;
    private EmbeddingModel embeddingModel;

    /* public OpenAIController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    } */

    public OpenAIController(ChatClient.Builder builder, EmbeddingModel embeddingModel) {
        this.chatMemory = MessageWindowChatMemory.builder().build();
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/search/{message}")
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

    @PostMapping("/recommend")
    public String recommend(@RequestParam String type, @RequestParam String year, @RequestParam String language) {

        String template = """
                I want to watch a {type} movie tonight with good rating,
                looking for movies around this year {year}.
                The language im looking for is {lang}.
                Suggest one specific movie and tell me the case and length of the movie.
                
                response format should be:
                1. Movie name
                2. basic plot
                3. case
                4. length
                5. IMDB rating
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of(
                "type", type,
                "year", year,
                "lang", language
        ));

        String response = chatClient
                .prompt(prompt)
                .call()
                .content();

        return response;
    }

    @PostMapping("/embedding")
    public float[] embedding(@RequestParam String text) {
        return embeddingModel.embed(text);
    }
}
