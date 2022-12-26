package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.mapper.UserDtoMapper;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import com.github.saintukrainian.chatapp.repository.ComplexUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  final ComplexUserRepository complexUserRepository;
  final UserDtoMapper userDtoMapper;

  public List<UserDto> findUsersBySearchRequest(SearchRequest request) {
    if (StringUtils.isEmpty(request.getSearchString())) {
      return List.of();
    }
    return userDtoMapper.mapToUserDtoList(complexUserRepository.findUsersBySearchRequest(request));
  }

}
