package com.github.saintukrainian.chatapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessagesSeenRequest {

  private Long chatId;
  private Long userId;
}
