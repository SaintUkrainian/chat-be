package com.github.saintukrainian.chatapp.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.saintukrainian.chatapp.jackson.serializer.LocalDateTimeSerializer;
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
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime sendTimestamp;
  private String value;
  private UserDto fromUser;
}
