package ma.khairy.chatBot.service;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message = "Le message ne peut pas être vide.")
        String message,
        @NotBlank(message = "L'ID de conversation ne peut pas être vide.")
        String conversationId
) { }
