package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.mapper.UserDtoMapper;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.model.request.LoginRequest;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import com.github.saintukrainian.chatapp.repository.ComplexUserRepository;
import com.github.saintukrainian.chatapp.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

  final UserRepository userRepository;
  final ComplexUserRepository complexUserRepository;
  final UserDtoMapper userDtoMapper;

  public UserDto findUserByLoginRequest(LoginRequest request) {
    return userDtoMapper.mapToUserDto(
        userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()));
  }

  public List<UserDto> findUsersBySearchRequest(SearchRequest request) {
    return userDtoMapper.mapToUserDtoList(complexUserRepository.findUsersBySearchRequest(request));
  }
}
