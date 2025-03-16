package ma.khairy.chatBot.service;

import ma.khairy.chatBot.entity.ChatMemoryEntity;
import ma.khairy.chatBot.repo.ChatMemoryRepository;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostgresChatMemory implements ChatMemory {

    private final ChatMemoryRepository repository;

    public PostgresChatMemory(ChatMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(String conversationId, List<Message> messages) {
        for (Message message : messages) {
            String sender = (message instanceof UserMessage) ? "user" : "assistant";
            ChatMemoryEntity entity = new ChatMemoryEntity(conversationId, sender, message.getText());
            repository.save(entity);
        }
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<ChatMemoryEntity> entities = repository.findByConversationIdOrderByTimestampDesc(conversationId);

        // Si lastN est positif, limiter le nombre de messages
        if (lastN > 0 && entities.size() > lastN) {
            entities = entities.subList(0, lastN);
        }

        // Trier par timestamp croissant pour obtenir l'ordre chronologique
        entities.sort(Comparator.comparing(ChatMemoryEntity::getTimestamp));

        return entities.stream()
                .map(entity -> {
                    if ("user".equals(entity.getSender())) {
                        return new UserMessage(entity.getContent());
                    } else {
                        return new AssistantMessage(entity.getContent());
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public void clear(String conversationId) {
        repository.deleteAll(repository.findByConversationIdOrderByTimestampDesc(conversationId));
    }
}