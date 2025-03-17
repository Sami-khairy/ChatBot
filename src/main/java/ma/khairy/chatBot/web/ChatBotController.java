package ma.khairy.chatBot.web;

import jakarta.validation.Valid;
import ma.khairy.chatBot.service.ChatBotService;
import ma.khairy.chatBot.service.ChatRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping("/chatV1")
    public String chatV1(@Valid @RequestBody ChatRequest request) {
        return chatBotService.simpleChat(request.message(), request.conversationId());
    }

    @GetMapping("/chatV2")
    public Flux<String> chatV2(String message){
        return chatBotService.fluxChat(message);
    }
}
