package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import com.github.saintukrainian.chatapp.mapper.UserDtoMapper;
import com.github.saintukrainian.chatapp.model.ChatUserDto;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import com.github.saintukrainian.chatapp.repository.ChatUserRepository;
import com.github.saintukrainian.chatapp.repository.ComplexChatUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatUserService {

  final ComplexChatUserRepository complexChatUserRepository;
  final ChatUserRepository chatUserRepository;
  final UserDtoMapper userDtoMapper;

  public void populateChatForUsers(ChatRequest request) {
    complexChatUserRepository.populateChatForUsers(request);
  }

  public ChatUserDto findChatUserByUserIdAndChatId(Long userId, Long chatId) {
    ChatUser chatUser = chatUserRepository.findChatUserByUserUserIdAndChatId(
        userId, chatId);
    return mapToChatUserDto(chatUser);
  }

  public boolean existsByUserIdAndChatWithUserId(Long userId, Long chatWithUserId) {
    return chatUserRepository.existsChatUserByUserUserIdAndChatWithUserUserId(userId,
        chatWithUserId);
  }

  public List<ChatUserDto> findChatsByUserId(Long userId) {
    log.info("Finding chats by user id: {}", userId);
    return chatUserRepository.findChatUsersByUserUserId(userId).stream()
        .map(this::mapToChatUserDto)
        .toList();
  }

  private ChatUserDto mapToChatUserDto(ChatUser chatUser) {
    return ChatUserDto.builder()
        .chatWithUser(userDtoMapper.mapToUserWithImageDto(chatUser.getChatWithUser()))
        .user(userDtoMapper.mapToUserWithImageDto(chatUser.getUser()))
        .chatId(chatUser.getChatId())
        .build();
  }
}
