package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.ChatMessageDto;
import com.github.saintukrainian.chatapp.service.ChatMessageFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-messages")
public class MessageController {

  final ChatMessageFacade chatMessageFacade;

  @GetMapping("/{chatId}")
  public ResponseEntity<List<ChatMessageDto>> fetchChatMessages(@PathVariable Long chatId) {
    return ResponseEntity.ok(chatMessageFacade.findMessagesByChatId(chatId));
  }
}
