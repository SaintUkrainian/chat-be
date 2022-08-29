package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.model.ChatRequest;
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
            + "VALUES (:chat_id, :user_id, :chat_with_user_id)");

    nativeQuery.setParameter("chat_id", request.getChatId());
    nativeQuery.setParameter("user_id", request.getFromUserId());
    nativeQuery.setParameter("chat_with_user_id", request.getToUserId());

    nativeQuery.executeUpdate();

    nativeQuery.setParameter("user_id", request.getToUserId());
    nativeQuery.setParameter("chat_with_user_id", request.getFromUserId());

    nativeQuery.executeUpdate();
  }
}
