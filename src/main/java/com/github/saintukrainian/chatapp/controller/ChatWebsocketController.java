package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import com.github.saintukrainian.chatapp.model.ChatRequest;
import com.github.saintukrainian.chatapp.repository.ChatUserRepository;
import com.github.saintukrainian.chatapp.service.ChatUserService;
import com.github.saintukrainian.chatapp.service.ComplexChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatWebsocketController {

  final ComplexChatService complexChatService;
  final ChatUserService chatUserService;
  final ChatUserRepository chatUserRepository;
  final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/websocket-new-chat")
  public void chatNew(ChatRequest chatRequest) {
    complexChatService.createNewChat(chatRequest);

    chatUserService.populateChatForUsers(chatRequest);

    ChatUser fromUserChat = chatUserRepository.findChatUserByUserUserIdAndChatId(
        chatRequest.getFromUserId(),
        chatRequest.getChatId());
    ChatUser toUserChat = chatUserRepository.findChatUserByUserUserIdAndChatId(
        chatRequest.getToUserId(),
        chatRequest.getChatId());

    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getFromUserId(),
        fromUserChat);
    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getToUserId(),
        toUserChat);
  }
}
