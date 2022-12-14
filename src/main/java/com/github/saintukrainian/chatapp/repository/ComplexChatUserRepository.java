package com.github.saintukrainian.chatapp.repository;

import static com.github.saintukrainian.chatapp.constants.QueryParams.CHAT_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.CHAT_WITH_USER_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.USER_ID;

import com.github.saintukrainian.chatapp.model.request.ChatRequest;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComplexChatUserRepository {

  final EntityManager entityManager;

  @Transactional
  public void populateChatForUsers(ChatRequest request) {
    log.info("Populating chat by chat request: {}", request);
    Query nativeQuery = entityManager.createNativeQuery(
        "INSERT INTO chat_user (chat_id, user_id, chat_with_user_id) "
            + "VALUES (:chatId, :userId, :chatWithUserId)");

    nativeQuery.setParameter(CHAT_ID, request.getChatId());
    nativeQuery.setParameter(USER_ID, request.getFromUserId());
    nativeQuery.setParameter(CHAT_WITH_USER_ID, request.getToUserId());

    nativeQuery.executeUpdate();

    nativeQuery.setParameter(USER_ID, request.getToUserId());
    nativeQuery.setParameter(CHAT_WITH_USER_ID, request.getFromUserId());

    nativeQuery.executeUpdate();
  }
}
