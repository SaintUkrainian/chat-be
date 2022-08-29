package com.github.saintukrainian.chatapp.utils;

import com.github.saintukrainian.chatapp.model.ChatMessageDto;
import com.github.saintukrainian.chatapp.model.ChatRequest;
import java.time.LocalDateTime;

public class DatePopulater {

  public static void populateWithTimestamp(ChatMessageDto message) {
    message.setSendTimestamp(LocalDateTime.now());
  }

  public static void populateWithTimestamp(ChatRequest chatRequest) {
    chatRequest.setCreateTimestamp(LocalDateTime.now());
  }
}
