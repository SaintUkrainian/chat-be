package com.github.saintukrainian.chatapp.utils;

import com.github.saintukrainian.chatapp.model.ChatMessage;
import java.time.OffsetDateTime;

public class DatePopulater {

  public static void populateWithTimestamp(ChatMessage message) {
    message.setTimestamp(OffsetDateTime.now());
  }
}
