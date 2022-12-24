package com.github.saintukrainian.chatapp.controller.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import com.github.saintukrainian.chatapp.model.request.MessagesSeenRequest;
import com.github.saintukrainian.chatapp.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageWebsocketController {

  public static final String PRIVATE_CHAT_BASE_PATH = "/topic/private-chat/";
  public static final String EDIT_MESSAGE_PATH = "/edit-message";
  public static final String DELETE_MESSAGE_PATH = "/delete-message";
  public static final String MESSAGES_SEEN_TOPIC = "messages-seen";
  final ChatMessageService chatMessageService;
  @Value("${spring.kafka.topic}")
  private String notificationsTopic;
  final SimpMessagingTemplate simpMessagingTemplate;

  final KafkaTemplate<String, String> kafkaTemplate;
  final ObjectMapper mapper;

  @MessageMapping("/websocket-private-chat")
  public void privateChat(ChatMessageDto message) throws JsonProcessingException {
    chatMessageService.saveChatMessage(message);

    message.setUnseenMessagesCount(
        chatMessageService.getCountOfUnseenMessagesByChatIdAndUserId(message.getChatId(),
            message.getFromUser().getUserId()));

    log.info("Sending message: {}", message);

    kafkaTemplate.send(notificationsTopic, mapper.writeValueAsString(message));

    simpMessagingTemplate.convertAndSend(PRIVATE_CHAT_BASE_PATH + message.getChatId(), message);
  }

  @MessageMapping("/websocket-private-chat/messages-seen")
  public void updateMessagesStatusToSeen(MessagesSeenRequest messagesSeenRequest)
      throws JsonProcessingException {

    log.info("Updating messages status to seen in chat: {}", messagesSeenRequest.getChatId());

    chatMessageService.updateMessagesSeenStatus(messagesSeenRequest.getChatId());

    kafkaTemplate.send(MESSAGES_SEEN_TOPIC, mapper.writeValueAsString(messagesSeenRequest));
  }

  @MessageMapping("/websocket-private-chat/edit-message")
  public void editMessage(ChatMessageDto message) throws JsonProcessingException {
    chatMessageService.editMessage(message);

    kafkaTemplate.send(notificationsTopic, mapper.writeValueAsString(message));

    simpMessagingTemplate.convertAndSend(
        PRIVATE_CHAT_BASE_PATH + message.getChatId() + EDIT_MESSAGE_PATH, message);
  }

  @MessageMapping("/websocket-private-chat/delete-message")
  public void deleteMessage(ChatMessageDto message) throws JsonProcessingException {
    message.setDeleted(true);
    chatMessageService.deleteMessage(message);

    ChatMessageDto latestMessageByChatId = chatMessageService.findLatestMessageByChatId(
        message.getChatId());
    latestMessageByChatId.setUnseenMessagesCount(
        chatMessageService.getCountOfUnseenMessagesByChatIdAndUserId(message.getChatId(),
            message.getFromUser().getUserId()));

    kafkaTemplate.send(notificationsTopic, mapper.writeValueAsString(latestMessageByChatId));

    simpMessagingTemplate.convertAndSend(
        PRIVATE_CHAT_BASE_PATH + message.getChatId() + DELETE_MESSAGE_PATH, message);
  }
}
