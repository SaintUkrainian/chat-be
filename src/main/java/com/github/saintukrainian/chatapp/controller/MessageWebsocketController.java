package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.ChatMessage;
import com.github.saintukrainian.chatapp.model.ChatRequest;
import com.github.saintukrainian.chatapp.service.MessageProducerService;
import com.github.saintukrainian.chatapp.utils.DatePopulater;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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

  @MessageMapping("/websocket-chat")
  @SendTo("/topic/chat")
  public ChatMessage chat(ChatMessage message) {
    DatePopulater.populateWithTimestamp(message);
    return message;
  }

  @MessageMapping("/websocket-new-chat")
  public void chatNew(ChatRequest chatRequest) {
    log.info("New chat request {}", chatRequest);
    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getToUser(),
        chatRequest);
  }
}
