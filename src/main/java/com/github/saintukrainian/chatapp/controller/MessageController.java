package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import com.github.saintukrainian.chatapp.service.ChatMessageService;
import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-messages")
public class MessageController {

  final ChatMessageService chatMessageService;

  @GetMapping("/{chatId}")
  public ResponseEntity<List<ChatMessageDto>> fetchChatMessages(@PathVariable Long chatId) {
    return ResponseEntity.ok(chatMessageService.findMessagesByChatId(chatId));
  }

  @GetMapping("/{chatId}/latest")
  public ResponseEntity<ChatMessageDto> fetchLatestChatMessage(@PathVariable Long chatId) {
    return ResponseEntity.ok(chatMessageService.findLatestMessageByChatId(chatId));
  }

  @GetMapping("/{chatId}/unseen")
  public ResponseEntity<BigInteger> fetchCountOfUnseenChatMessages(@PathVariable Long chatId) {
    return ResponseEntity.ok(chatMessageService.getCountOfUnseenMessagesByChatId(chatId));
  }

  @PostMapping("/{chatId}/seen")
  public ResponseEntity<Boolean> updateMessagesSeenStatus(@PathVariable Long chatId) {
    return ResponseEntity.ok(chatMessageService.updateMessagesSeenStatus(chatId));
  }
}
