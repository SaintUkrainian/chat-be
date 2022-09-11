package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import com.github.saintukrainian.chatapp.repository.ChatUserRepository;
import com.github.saintukrainian.chatapp.repository.ComplexChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatUserFacade {

  final ComplexChatUserRepository complexChatUserRepository;
  final ChatUserRepository chatUserRepository;

  public void populateChatForUsers(ChatRequest request) {
    complexChatUserRepository.populateChatForUsers(request);
  }

  public ChatUser findChatUserByUserIdAndChatId(Long userId, Long chatId) {
    return chatUserRepository.findChatUserByUserUserIdAndChatId(userId, chatId);
  }
}
