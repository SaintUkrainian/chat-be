package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.model.ChatMessageDto;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComplexChatMessageRepository {

  final EntityManager entityManager;

  @Transactional
  public void saveMessage(ChatMessageDto chatMessageDto) {
    log.info("Saving new message: {}", chatMessageDto);
    Query nativeQuery = entityManager.createNativeQuery(
        "INSERT INTO chat_message (chat_id, value, from_user_id, send_timestamp) "
            + "VALUES (:chatId, :value, :fromUserId, :sendTimestamp)");

    nativeQuery.setParameter("chatId", chatMessageDto.getChatId());
    nativeQuery.setParameter("value", chatMessageDto.getValue());
    nativeQuery.setParameter("fromUserId", chatMessageDto.getFromUser().getUserId());
    nativeQuery.setParameter("sendTimestamp", chatMessageDto.getSendTimestamp());

    nativeQuery.executeUpdate();
    log.info("Message saved");
  }
}
