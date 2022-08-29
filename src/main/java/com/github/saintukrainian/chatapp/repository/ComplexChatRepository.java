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
public class ComplexChatRepository {

  final EntityManager entityManager;

  @Transactional
  public void createNewChat(ChatRequest chatRequest) {
    log.info("Creating new chat by Chat Request: {}", chatRequest);
    Query nativeQuery = entityManager.createNativeQuery(
        "INSERT INTO chat (created_by_user_id, to_user_id, create_timestamp) "
            + "VALUES (:createdByUserId, :toUserId, :createTimestamp)");

    Long fromUserId = chatRequest.getFromUserId();
    nativeQuery.setParameter("createdByUserId", fromUserId);
    nativeQuery.setParameter("toUserId", chatRequest.getToUserId());
    nativeQuery.setParameter("createTimestamp", chatRequest.getCreateTimestamp());

    nativeQuery.executeUpdate();
    log.info("Chat created by user id = {}", fromUserId);
  }
}
