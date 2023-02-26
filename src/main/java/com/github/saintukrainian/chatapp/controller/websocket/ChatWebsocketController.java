package com.github.saintukrainian.chatapp.controller.websocket;

import com.github.saintukrainian.chatapp.exception.ChatCreationException;
import com.github.saintukrainian.chatapp.model.ChatUserDto;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import com.github.saintukrainian.chatapp.service.ChatFacade;
import com.github.saintukrainian.chatapp.service.ChatUserService;
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
  final ChatUserService chatUserService;
  final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/websocket-new-chat")
  public void chatNew(ChatRequest chatRequest) {
    boolean isTheSameUser = chatRequest.getFromUserId().equals(chatRequest.getToUserId());
    boolean alreadyExistsForTheSameUsers = chatUserService.existsByUserIdAndChatWithUserId(
        chatRequest.getFromUserId(),
        chatRequest.getToUserId());
    if (isTheSameUser || alreadyExistsForTheSameUsers) {
      log.error("Chat cannot be created due to bad request or chat already exists, {}",
          chatRequest);
      throw new ChatCreationException(
          "Chat cannot be created due to bad request or chat already exists");
    }
    chatFacade.createNewChat(chatRequest);

    chatUserService.populateChatForUsers(chatRequest);

    ChatUserDto fromUserChat = chatUserService.findChatUserByUserIdAndChatId(
        chatRequest.getFromUserId(),
        chatRequest.getChatId());
    ChatUserDto toUserChat = chatUserService.findChatUserByUserIdAndChatId(
        chatRequest.getToUserId(),
        chatRequest.getChatId());

    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getFromUserId(),
        fromUserChat);
    simpMessagingTemplate.convertAndSend("/topic/new-chat/" + chatRequest.getToUserId(),
        toUserChat);
  }

  @MessageMapping("/websocket-delete-chat")
  public void deleteChatForBothUsers(ChatRequest chatRequest) {
    log.info("Deleting chat by request: {}", chatRequest);
    Long chatId = chatRequest.getChatId();

    chatFacade.deleteChat(chatId);

    simpMessagingTemplate.convertAndSend("/topic/delete-chat/" + chatRequest.getFromUserId(),
        chatId);
    simpMessagingTemplate.convertAndSend("/topic/delete-chat/" + chatRequest.getToUserId(),
        chatId);
  }
}
