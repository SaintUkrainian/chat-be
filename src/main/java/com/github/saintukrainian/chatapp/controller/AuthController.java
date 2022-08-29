package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.LoginRequest;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(userService.findUserByLoginRequest(loginRequest));
  }
}
