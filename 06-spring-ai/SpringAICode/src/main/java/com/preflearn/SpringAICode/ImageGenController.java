package com.preflearn.SpringAICode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageGenController {

    private final ChatClient chatClient;
    private final OpenAiImageModel openAiImageModel;

    public ImageGenController(ChatModel chatModel, OpenAiImageModel openAiImageModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/generate/{query}")
    public String generateImage(@PathVariable String query) {
        ImagePrompt imagePrompt = new ImagePrompt(
                query,
                OpenAiImageOptions.builder()
                        .quality("hd")
                        .height(1024)
                        .width(1024)
                        .style("natural")
                        .build()
        );
        ImageResponse response = openAiImageModel.call(imagePrompt);
        return response.getResult().getOutput().getUrl();
    }
}
