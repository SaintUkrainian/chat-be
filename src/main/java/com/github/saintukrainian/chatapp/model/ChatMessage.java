package com.github.saintukrainian.chatapp.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

  private OffsetDateTime timestamp;
  private String value;
  private String fromUser;
  private String toUser;
}
