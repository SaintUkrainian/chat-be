package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.model.LoginRequest;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.repository.ComplexUserRepository;
import com.github.saintukrainian.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

  final UserRepository userRepository;
  final ComplexUserRepository complexUserRepository;

  public UserDto findUserByLoginRequest(LoginRequest request) {
    User user = userRepository.findByUsernameAndPassword(request.getUsername(),
        request.getPassword());

    return UserDto.builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .username(user.getUsername())
        .build();
  }
}
