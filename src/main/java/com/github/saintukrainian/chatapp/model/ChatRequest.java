package com.github.saintukrainian.chatapp.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {

  private Long chatId;
  private Long fromUserId;
  private Long toUserId;
  private LocalDateTime createTimestamp;

}
