package com.github.saintukrainian.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDto {

  private Long userId;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
}
