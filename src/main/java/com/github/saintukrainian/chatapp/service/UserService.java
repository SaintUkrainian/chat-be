package com.github.saintukrainian.chatapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.saintukrainian.chatapp.mapper.UserDtoMapper;
import com.github.saintukrainian.chatapp.model.UpdatedUserData;
import com.github.saintukrainian.chatapp.model.UpdatedUserData.UpdatedUserDataBuilder;
import com.github.saintukrainian.chatapp.model.UpdatedUserName;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import com.github.saintukrainian.chatapp.repository.ComplexUserRepository;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  final ComplexUserRepository complexUserRepository;
  final UserDtoMapper userDtoMapper;
  final UserImageService userImageService;
  final ObjectMapper objectMapper;

  public List<UserDto> findUsersBySearchRequest(SearchRequest request) {
    if (StringUtils.isEmpty(request.getSearchString())) {
      return List.of();
    }
    return userDtoMapper.mapToUserDtoList(complexUserRepository.findUsersBySearchRequest(request));
  }

  public UpdatedUserData updateUserData(Long userId, MultipartFile rawImage, String updatedUserNameString)
      throws IOException {
    log.info("Updating user data for user id: {}", userId);

    UpdatedUserDataBuilder<?, ?> updatedUserDataBuilder = UpdatedUserData.builder();

    if (userId == null) {
      throw new IllegalArgumentException("User Id cannot be null");
    }

    if (rawImage != null) {
      log.info("Updating user image");
      updatedUserDataBuilder.userImage(userImageService.saveImageForUser(userId, rawImage));
    }

    if (updatedUserNameString != null) {
      log.info("Updating user name");
      UpdatedUserName updatedUserName = objectMapper.readValue(updatedUserNameString,
          new TypeReference<>() {
          });

      complexUserRepository.updateUserName(userId, updatedUserName);

      updatedUserDataBuilder.firstName(updatedUserName.getFirstName());
      updatedUserDataBuilder.lastName(updatedUserName.getLastName());
    }

    return updatedUserDataBuilder.build();
  }

}
