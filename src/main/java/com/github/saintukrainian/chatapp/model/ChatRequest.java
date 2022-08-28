package com.github.saintukrainian.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {

  private String chatId;
  private String fromUser;
  private String toUser;
}
