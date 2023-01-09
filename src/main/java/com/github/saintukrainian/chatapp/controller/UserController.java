package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.model.UpdatedUserData;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import com.github.saintukrainian.chatapp.service.UserService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  final UserService userService;

  @PostMapping
  public List<UserDto> findUsersBySearchRequest(@RequestBody SearchRequest searchRequest) {
    return userService.findUsersBySearchRequest(searchRequest);
  }

  @PostMapping("/{userId}/update-user-data")
  public ResponseEntity<UpdatedUserData> updateUserData(@PathVariable Long userId,
      @RequestParam(value = "image", required = false) MultipartFile rawImage,
      @RequestParam(value = "updatedUserName", required = false) String updatedUserName)
      throws IOException {
    return ResponseEntity.ok(userService.updateUserData(userId, rawImage, updatedUserName));
  }

}
