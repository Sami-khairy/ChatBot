package ma.khairy.chatBot.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_memory")
@Getter
@Setter
@NoArgsConstructor
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
}

