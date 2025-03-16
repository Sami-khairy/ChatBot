package ma.khairy.chatBot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatBotService {
    private final ChatClient chatClient;

    public ChatBotService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                .build();
    }

    public String simpleChat(String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
    public Flux<String> fluxChat(String message){
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
