package com.github.saintukrainian.chatapp.utils;

import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatePopulater {

  public static void populateWithTimestamp(ChatMessageDto message) {
    message.setSendTimestamp(LocalDateTime.now());
  }

  public static void populateWithTimestamp(ChatRequest chatRequest) {
    chatRequest.setCreateTimestamp(LocalDateTime.now());
  }
}
