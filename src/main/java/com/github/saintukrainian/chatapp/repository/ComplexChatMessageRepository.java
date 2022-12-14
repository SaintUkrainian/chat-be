package com.github.saintukrainian.chatapp.repository;

import static com.github.saintukrainian.chatapp.constants.QueryParams.MESSAGE_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.NEW_VALUE;

import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
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
  public void editMessage(ChatMessageDto message) {
    Query nativeQuery = entityManager.createNativeQuery(
        "UPDATE chat_message SET value = :newValue, is_edited = true WHERE message_id = :messageId");
    nativeQuery.setParameter(MESSAGE_ID, message.getMessageId());
    nativeQuery.setParameter(NEW_VALUE, message.getValue());
    nativeQuery.executeUpdate();
  }
}
