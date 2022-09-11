package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.Chat;
import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import com.github.saintukrainian.chatapp.repository.ChatRepository;
import com.github.saintukrainian.chatapp.utils.DatePopulater;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatFacade {

  final ChatRepository chatRepository;

  @Transactional
  public void createNewChat(ChatRequest chatRequest) {

    DatePopulater.populateWithTimestamp(chatRequest);

    Chat createdChat = chatRepository.save(Chat.builder()
        .createdByUserId(chatRequest.getFromUserId())
        .createTimestamp(chatRequest.getCreateTimestamp())
        .build());

    log.info("New chat created: {}", createdChat);

    chatRequest.setChatId(createdChat.getChatId());
  }
}
