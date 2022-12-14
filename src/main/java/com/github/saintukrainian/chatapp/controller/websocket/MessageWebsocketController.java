package com.github.saintukrainian.chatapp.controller.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import com.github.saintukrainian.chatapp.service.ChatMessageFacade;
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

  @Value("${spring.kafka.topic}")
  private String topic;
  final ChatMessageFacade chatMessageFacade;
  final SimpMessagingTemplate simpMessagingTemplate;

  final KafkaTemplate<String, String> kafkaTemplate;
  final ObjectMapper mapper;

  @MessageMapping("/websocket-private-chat")
  public void privateChat(ChatMessageDto message) throws JsonProcessingException {
    chatMessageFacade.saveChatMessage(message);

    log.info("Sending message: {}", message);

    kafkaTemplate.send(topic, mapper.writeValueAsString(message));

    simpMessagingTemplate.convertAndSend(PRIVATE_CHAT_BASE_PATH + message.getChatId(), message);
  }

  @MessageMapping("/websocket-private-chat/edit-message")
  public void editMessage(ChatMessageDto message) throws JsonProcessingException {
    chatMessageFacade.editMessage(message);


    kafkaTemplate.send(topic, mapper.writeValueAsString(message));

    simpMessagingTemplate.convertAndSend(
        PRIVATE_CHAT_BASE_PATH + message.getChatId() + EDIT_MESSAGE_PATH, message);
  }

  @MessageMapping("/websocket-private-chat/delete-message")
  public void deleteMessage(ChatMessageDto message) throws JsonProcessingException {
    message.setDeleted(true);
    chatMessageFacade.deleteMessage(message);

    kafkaTemplate.send(topic, mapper.writeValueAsString(message));

    simpMessagingTemplate.convertAndSend(
        PRIVATE_CHAT_BASE_PATH + message.getChatId() + DELETE_MESSAGE_PATH, message);
  }
}
