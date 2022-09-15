package com.github.saintukrainian.chatapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String email;
}
