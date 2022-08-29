package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import com.github.saintukrainian.chatapp.repository.ChatUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {

  final ChatUserRepository chatUserRepository;

  @GetMapping("/{userId}")
  public ResponseEntity<List<ChatUser>> fetchChatsByUser(@PathVariable Long userId) {
    return ResponseEntity.ok(chatUserRepository.findChatUsersByUserUserId(userId));
  }
}
