package com.github.saintukrainian.chatapp.service;

import com.github.saintukrainian.chatapp.entity.ChatMessage;
import com.github.saintukrainian.chatapp.entity.ChatMessageSimplified;
import com.github.saintukrainian.chatapp.mapper.UserDtoMapper;
import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import com.github.saintukrainian.chatapp.repository.ChatMessageRepository;
import com.github.saintukrainian.chatapp.repository.ChatMessageSimplifiedRepository;
import com.github.saintukrainian.chatapp.repository.ComplexChatMessageRepository;
import com.github.saintukrainian.chatapp.utils.DatePopulater;
import java.math.BigInteger;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageService {

  final ChatMessageRepository chatMessageRepository;
  final ChatMessageSimplifiedRepository chatMessageSimplifiedRepository;
  final ComplexChatMessageRepository complexChatMessageRepository;
  final UserDtoMapper userDtoMapper;

  public List<ChatMessageDto> findMessagesByChatId(Long chatId) {
    log.info("Finding chat messages by chat id: {}", chatId);
    List<ChatMessage> chatMessages =
        chatMessageRepository.findChatMessagesByChatChatIdOrderBySendTimestampAsc(chatId);

    return chatMessages.stream()
        .map(this::mapToMessageDto)
        .toList();
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

    ChatMessageSimplified savedMessage = chatMessageSimplifiedRepository.save(
        ChatMessageSimplified.builder()
            .chatId(messageDto.getChatId())
            .fromUserId(messageDto.getFromUser().getUserId())
            .value(messageDto.getValue())
            .sendTimestamp(messageDto.getSendTimestamp())
            .seen(messageDto.isSeen())
            .build());

    messageDto.setMessageId(savedMessage.getMessageId());
  }

  public ChatMessageDto findLatestMessageByChatId(Long chatId) {
    return mapToMessageDto(
        chatMessageRepository.findTopByChatChatIdOrderBySendTimestampDesc(chatId));
  }

  public BigInteger getCountOfUnseenMessagesByChatId(Long chatId) {
    return complexChatMessageRepository.getCountOfUnseenMessagesByChatId(chatId);
  }

  public BigInteger getCountOfUnseenMessagesByChatIdAndUserId(Long chatId, Long userId) {
    return complexChatMessageRepository.getCountOfUnseenMessagesByChatIdAndUserId(chatId, userId);
  }

  private ChatMessageDto mapToMessageDto(ChatMessage message) {
    if (message == null) {
      return new ChatMessageDto();
    }
    return ChatMessageDto.builder()
        .chatId(message.getChat().getChatId())
        .edited(message.isEdited())
        .messageId(message.getMessageId())
        .sendTimestamp(message.getSendTimestamp())
        .value(message.getValue())
        .fromUser(userDtoMapper.mapToUserDto(message.getFromUser()))
        .build();
  }

  public Boolean updateMessagesSeenStatus(Long chatId) {
    return complexChatMessageRepository.updateMessagesSeenStatus(chatId);
  }
}
