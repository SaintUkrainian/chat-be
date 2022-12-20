package com.github.saintukrainian.chatapp.utils;

import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DatePopulater {

  public static void populateWithTimestamp(ChatMessageDto message) {
    log.info("Populating with timestamp when MESSAGE was SENT. Message: {}", message);
    message.setSendTimestamp(LocalDateTime.now());
  }

  public static void populateWithTimestamp(ChatRequest chatRequest) {
    log.info("Populating with timestamp when CHAT was CREATED. Chat Request: {}", chatRequest);
    chatRequest.setCreateTimestamp(LocalDateTime.now());
  }
}
