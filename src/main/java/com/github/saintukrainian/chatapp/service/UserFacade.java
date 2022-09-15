package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.mapper.UserDtoMapper;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.model.request.LoginRequest;
import com.github.saintukrainian.chatapp.model.request.RegistrationRequest;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import com.github.saintukrainian.chatapp.repository.ComplexUserRepository;
import com.github.saintukrainian.chatapp.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
    if (StringUtils.isEmpty(request.getSearchString())) {
      return List.of();
    }
    return userDtoMapper.mapToUserDtoList(complexUserRepository.findUsersBySearchRequest(request));
  }

  public UserDto registerNewUser(RegistrationRequest registrationRequest) {
    log.info("Registration of a new user by request: {}", registrationRequest);
    User user = userRepository.save(User.builder()
        .username(registrationRequest.getUsername())
        .password(registrationRequest.getPassword())
        .firstName(registrationRequest.getFirstName())
        .lastName(registrationRequest.getLastName())
        .email(registrationRequest.getEmail())
        .build());
    log.info("User has been registered with id: {}", user.getUserId());
    return userDtoMapper.mapToUserDto(user);
  }
}
