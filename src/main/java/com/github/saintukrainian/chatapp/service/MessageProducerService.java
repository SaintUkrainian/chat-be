package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.model.ChatMessage;
import com.github.saintukrainian.chatapp.utils.DatePopulater;
import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageProducerService {

  private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

  final SimpMessagingTemplate simpMessagingTemplate;

  @Value("${spring.kafka.topic}")
  private String topic;

  public void produceMessage(ChatMessage message, Principal principal) {
    populateWithTimestamp(message, principal);

    sendMessageToUser(message, principal);

    Message<ChatMessage> kafkaMessage = MessageBuilder.withPayload(message)
        .setHeader(KafkaHeaders.TOPIC, topic)
        .build();
    kafkaTemplate.send(kafkaMessage);
  }

  private void sendMessageToUser(ChatMessage message, Principal principal) {
    log.info("Sending message from <{}> to <{}>: \"{}\"", principal.getName(), message.getToUser(),
        message.getValue());
    simpMessagingTemplate.convertAndSendToUser(message.getToUser(), "/topic/private-message",
        message);
  }

  private static void populateWithTimestamp(ChatMessage message, Principal principal) {
    DatePopulater.populateWithTimestamp(message);
    log.info("Message received from user {} at {}", principal.getName(),
        message.getTimestamp().format(
            DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss")));
  }
}
