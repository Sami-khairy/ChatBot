package ma.khairy.chatBot.web;

import ma.khairy.chatBot.service.ChatBotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping("/chatV1")
    public String chatV1(String message){
        return chatBotService.simpleChat(message);
    }

    @GetMapping("/chatV2")
    public Flux<String> chatV2(String message){
        return chatBotService.fluxChat(message);
    }
}
