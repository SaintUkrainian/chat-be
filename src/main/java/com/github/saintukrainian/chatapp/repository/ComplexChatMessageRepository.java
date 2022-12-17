package com.github.saintukrainian.chatapp.repository;

import static com.github.saintukrainian.chatapp.constants.QueryParams.CHAT_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.MESSAGE_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.NEW_VALUE;
import static com.github.saintukrainian.chatapp.constants.QueryParams.USER_ID;

import com.github.saintukrainian.chatapp.context.UserContext;
import com.github.saintukrainian.chatapp.model.request.ChatMessageDto;
import java.math.BigInteger;
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

  public BigInteger getCountOfUnseenMessagesByChatId(long chatId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT COUNT(message_id) FROM chat_message WHERE chat_id = :chatId "
            + "AND from_user_id != :userId "
            + "AND NOT is_seen");

    nativeQuery.setParameter(CHAT_ID, chatId);
    nativeQuery.setParameter(USER_ID, UserContext.getUserId());

    return (BigInteger) nativeQuery.getSingleResult();
  }

  public BigInteger getCountOfUnseenMessagesByChatIdAndUserId(long chatId, long userId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT COUNT(message_id) FROM chat_message WHERE chat_id = :chatId "
            + "AND from_user_id = :userId "
            + "AND NOT is_seen");

    nativeQuery.setParameter(CHAT_ID, chatId);
    nativeQuery.setParameter(USER_ID, userId);

    return (BigInteger) nativeQuery.getSingleResult();
  }

  @Transactional
  public Boolean updateMessagesSeenStatus(Long chatId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "UPDATE chat_message SET is_seen = TRUE "
            + "WHERE chat_id = :chatId AND from_user_id != :userId");
    nativeQuery.setParameter(CHAT_ID, chatId);
    nativeQuery.setParameter(USER_ID, UserContext.getUserId());

    return nativeQuery.executeUpdate() == 1;
  }

}
