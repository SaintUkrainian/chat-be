package com.github.saintukrainian.chatapp.controller.websocket;

import com.github.saintukrainian.chatapp.model.ChatMessageDto;
import com.github.saintukrainian.chatapp.service.ChatMessageFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageWebsocketController {

  final ChatMessageFacade chatMessageFacade;
  final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/websocket-private-chat")
  public void privateChat(ChatMessageDto message) {
    chatMessageFacade.saveChatMessage(message);

    log.info("Sending message: {}", message);

    simpMessagingTemplate.convertAndSend("/topic/private-chat/" + message.getChatId(), message);
  }

  @MessageMapping("/websocket-private-chat/edit-message")
  public void editMessage(ChatMessageDto message) {
    log.info("Editing ");
  }
}
