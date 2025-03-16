package ma.khairy.chatBot.repo;

import ma.khairy.chatBot.entity.ChatMemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMemoryRepository extends JpaRepository<ChatMemoryEntity, Long> {
    List<ChatMemoryEntity> findByConversationIdOrderByTimestampDesc(String conversationId);
}
