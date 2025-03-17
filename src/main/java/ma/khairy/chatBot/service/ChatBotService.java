package ma.khairy.chatBot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class ChatBotService {
    private final ChatClient chatClient;

    public ChatBotService(ChatClient.Builder chatClient, PostgresChatMemory postgresChatMemory) {
        this.chatClient = chatClient
                .defaultSystem("You are a friendly chat bot that always responds in a friendly tone")
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(postgresChatMemory)
                )
                .build();
    }

    public String simpleChat(String message, String conversationId){
        return chatClient.prompt()
                .advisors(advisor -> advisor.param("chat_memory_conversation_id", conversationId))
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
