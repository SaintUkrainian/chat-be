package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.ChatMessage;
import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.model.ChatMessageDto;
import com.github.saintukrainian.chatapp.model.UserDto;
import com.github.saintukrainian.chatapp.repository.ChatMessageRepository;
import com.github.saintukrainian.chatapp.repository.ComplexChatMessageRepository;
import com.github.saintukrainian.chatapp.utils.DatePopulater;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageFacade {

  final ChatMessageRepository chatMessageRepository;
  final ComplexChatMessageRepository complexChatMessageRepository;

  public List<ChatMessageDto> findMessagesByChatId(Long chatId) {
    log.info("Finding chat messages by chat id: {}", chatId);
    List<ChatMessage> chatMessages =
        chatMessageRepository.findChatMessagesByChatChatIdOrderBySendTimestampAsc(chatId);

    return chatMessages.stream()
        .map(ChatMessageFacade::mapToMessageDto)
        .collect(Collectors.toList());
  }

  public void editMessage(ChatMessageDto message) {
    log.info("Updated message received: {}", message);
    message.setEdited(true);
    complexChatMessageRepository.editMessage(message);
  }

  @Transactional
  public void deleteMessage(ChatMessageDto message) {
    log.info("Deleting message by id. {}", message);
    chatMessageRepository.deleteById(message.getMessageId());
  }

  public void saveChatMessage(ChatMessageDto messageDto) {
    log.info("Saving message: {}", messageDto);
    DatePopulater.populateWithTimestamp(messageDto);
    complexChatMessageRepository.saveMessage(messageDto);
  }

  private static ChatMessageDto mapToMessageDto(ChatMessage message) {
    return ChatMessageDto.builder()
        .chatId(message.getChat().getChatId())
        .edited(message.isEdited())
        .messageId(message.getMessageId())
        .sendTimestamp(message.getSendTimestamp())
        .value(message.getValue())
        .fromUser(mapToUserDto(message.getFromUser()))
        .build();
  }

  private static UserDto mapToUserDto(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .username(user.getUsername())
        .build();
  }
}
