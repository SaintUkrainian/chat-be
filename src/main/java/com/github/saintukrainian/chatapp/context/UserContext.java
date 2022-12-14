package com.github.saintukrainian.chatapp.context;

import java.security.Principal;

public class UserContext {

  public static Principal CURRENT_USER = null;

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
