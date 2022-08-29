package com.github.saintukrainian.chatapp.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDto {

  private Long chatId;
  private LocalDateTime sendTimestamp;
  private String value;
  private UserDto fromUser;
}
