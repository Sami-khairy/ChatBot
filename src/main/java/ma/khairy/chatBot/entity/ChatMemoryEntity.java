package ma.khairy.chatBot.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "chat_memory",
        indexes = {
                @Index(name = "idx_conversation", columnList = "conversation_id"),
                @Index(name = "idx_timestamp", columnList = "timestamp"),
        }
)
public class ChatMemoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversation_id", nullable = false)
    private String conversationId;

    @Column(name = "sender", nullable = false)
    private String sender; // "user" ou "assistant"

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public ChatMemoryEntity(String conversationId, String sender, String content) {
        this.conversationId = conversationId;
        this.sender = sender;
        this.content = content;
    }

    public ChatMemoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

