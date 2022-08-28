package com.github.saintukrainian.chatapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

  private static List<String> AVAILABLE_USERS = List.of("Denys", "Ben", "Josh");

  @GetMapping("/available")
  public List<String> fetchAllAvailableUsers() {
    return AVAILABLE_USERS;
  }
}
