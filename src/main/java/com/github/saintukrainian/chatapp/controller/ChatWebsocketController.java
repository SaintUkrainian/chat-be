package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.ChatRequest;
import com.github.saintukrainian.chatapp.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatWebsocketController {

  final SimpMessagingTemplate simpMessagingTemplate;
  final ChatRepository chatRepository;

  @MessageMapping("/websocket-new-chat")
  public void chatNew(ChatRequest chatRequest) {
    log.info("New chat request {}", chatRequest);
    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getToUserId(),
        chatRequest);
  }
}
