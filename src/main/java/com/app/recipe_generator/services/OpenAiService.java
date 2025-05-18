package com.app.recipe_generator.services;

import com.app.recipe_generator.DTO.RecipeResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAiService {

    private final ChatModel chatModel;
    private final OpenAiImageModel openAiImageModel;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public OpenAiService(ChatModel chatModel, OpenAiImageModel openAiImageModel) {
        this.chatModel = chatModel;
        this.openAiImageModel = openAiImageModel;
    }

    public List<RecipeResponseDTO> generateRecipes(String promptText) {
        Prompt prompt = new Prompt(
                promptText,
                OpenAiChatOptions.builder()
                        .model("gpt-4o")
                        .temperature(0.4)
                        .build()
        );

        ChatResponse response = chatModel.call(prompt);
        String rawContent = response.getResult().getOutput().getText();

        // Strip Markdown code block if present (e.g., ```json ... ```)
        String json = rawContent.replaceAll("(?s).*?```json\\s*(\\[.*?])\\s*```.*", "$1");

        try {
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response: " + e.getMessage(), e);
        }
    }

    public String generateRecipeImg(String title) {
        ImagePrompt imagePrompt = new ImagePrompt(
                "High-quality food photo of " + title,
                OpenAiImageOptions.builder()
                        .model("dall-e-3")
                        .quality("standard")
                        .N(1)
                        .height(1024)
                        .width(1024)
                        .build()
        );

        ImageResponse response = openAiImageModel.call(imagePrompt);
        return response.getResults().get(0).getOutput().getUrl();
    }
}
