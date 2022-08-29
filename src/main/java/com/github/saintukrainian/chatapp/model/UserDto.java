package com.github.saintukrainian.chatapp.model;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

  @Column(name = "user_id")
  private Long userId;
  private String username;
  private String email;
}
