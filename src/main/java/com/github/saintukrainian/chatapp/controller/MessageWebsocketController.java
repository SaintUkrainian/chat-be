package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.ChatMessage;
import com.github.saintukrainian.chatapp.service.MessageProducerService;
import com.github.saintukrainian.chatapp.utils.DatePopulater;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageWebsocketController {

  final MessageProducerService messageProducerService;
  final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/websocket-private-message")
  public void sendMessage(@Payload ChatMessage message, Principal principal) {
    messageProducerService.produceMessage(message, principal);
  }

  @MessageMapping("/websocket-private-chat/{chatId}")
  public void privateChat(@DestinationVariable String chatId, ChatMessage message) {
    log.info("Chat {} message: {}", chatId, message);
    DatePopulater.populateWithTimestamp(message);
    simpMessagingTemplate.convertAndSend("/topic/private-chat/" + chatId, message);
  }
}
