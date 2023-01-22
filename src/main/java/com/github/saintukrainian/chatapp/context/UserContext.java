package com.github.saintukrainian.chatapp.context;

import java.security.Principal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContext {

  private static Principal CURRENT_USER;

  public static void login(Principal principal) {
    CURRENT_USER = principal;
  }

  public static Long getUserId() {
    if (CURRENT_USER == null) {
      throw new IllegalArgumentException();
    }
    return Long.parseLong(CURRENT_USER.getName());
  }
}
