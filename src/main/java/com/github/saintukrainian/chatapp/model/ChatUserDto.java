package com.github.saintukrainian.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatUserDto {

  private Long chatId;

  private UserWithImageDto user;

  private UserWithImageDto chatWithUser;
}
