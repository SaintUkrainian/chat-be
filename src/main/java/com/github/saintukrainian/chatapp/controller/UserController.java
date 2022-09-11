package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import com.github.saintukrainian.chatapp.service.UserFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  final UserFacade userFacade;

  @PostMapping
  public List<UserDto> findUsersBySearchRequest(@RequestBody SearchRequest searchRequest) {
    return userFacade.findUsersBySearchRequest(searchRequest);
  }
}
