package com.github.saintukrainian.chatapp.controller.websocket;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import com.github.saintukrainian.chatapp.service.ChatFacade;
import com.github.saintukrainian.chatapp.service.ChatUserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatWebsocketController {

  final ChatFacade chatFacade;
  final ChatUserFacade chatUserFacade;
  final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/websocket-new-chat")
  public void chatNew(ChatRequest chatRequest) {
    if (chatRequest.getFromUserId().equals(chatRequest.getToUserId())) {
      throw new IllegalArgumentException("Chat cannot be created with yourself!");
    }
    chatFacade.createNewChat(chatRequest);

    chatUserFacade.populateChatForUsers(chatRequest);

    ChatUser fromUserChat = chatUserFacade.findChatUserByUserIdAndChatId(
        chatRequest.getFromUserId(),
        chatRequest.getChatId());
    ChatUser toUserChat = chatUserFacade.findChatUserByUserIdAndChatId(
        chatRequest.getToUserId(),
        chatRequest.getChatId());

    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getFromUserId(),
        fromUserChat);
    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getToUserId(),
        toUserChat);
  }
}
