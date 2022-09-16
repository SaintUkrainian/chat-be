package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.exception.LoginFailException;
import com.github.saintukrainian.chatapp.exception.RegistrationFailException;
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
    log.info("Logging in by login request: {}", request);
    User foundUser = userRepository.findByUsernameAndPassword(request.getUsername(),
        request.getPassword());

    if (foundUser == null) {
      throw new LoginFailException("Invalid Username or Password");
    }
    return userDtoMapper.mapToUserDto(foundUser);
  }

  public List<UserDto> findUsersBySearchRequest(SearchRequest request) {
    if (StringUtils.isEmpty(request.getSearchString())) {
      return List.of();
    }
    return userDtoMapper.mapToUserDtoList(complexUserRepository.findUsersBySearchRequest(request));
  }

  public UserDto registerNewUser(RegistrationRequest registrationRequest) {
    log.info("Registration of a new user by registration request: {}", registrationRequest);

    String email = registrationRequest.getEmail();
    String username = registrationRequest.getUsername();
    if (userRepository.existsByUsernameOrEmail(username, email)) {
      throw new RegistrationFailException("Username and Email must be unique");
    }

    User user = userRepository.save(User.builder()
        .username(username)
        .password(registrationRequest.getPassword())
        .firstName(registrationRequest.getFirstName())
        .lastName(registrationRequest.getLastName())
        .email(email)
        .build());
    log.info("User has been registered with id: {}", user.getUserId());
    return userDtoMapper.mapToUserDto(user);
  }
}
