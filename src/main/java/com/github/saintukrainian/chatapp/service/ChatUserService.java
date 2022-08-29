package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.model.ChatRequest;
import com.github.saintukrainian.chatapp.repository.ComplexChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatUserService {

  final ComplexChatUserRepository complexChatUserRepository;

  public void populateChatForUsers(ChatRequest request) {
    complexChatUserRepository.populateChatForUsers(request);
  }
}
