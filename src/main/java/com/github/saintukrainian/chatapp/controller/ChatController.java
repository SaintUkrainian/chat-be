package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.ChatUserDto;
import com.github.saintukrainian.chatapp.service.ChatUserService;
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

  final ChatUserService chatUserService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<ChatUserDto>> fetchChatsByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(chatUserService.findChatsByUserId(userId));
  }
}
