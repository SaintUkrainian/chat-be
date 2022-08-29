package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.model.LoginRequest;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComplexUserRepository {

  final EntityManager entityManager;

  public User findUserByLoginRequest(LoginRequest loginRequest) {
    log.info("Finding user by login request {}", loginRequest);
    Query nativeQuery = entityManager.createNativeQuery(
        "SELECT user_id, email, username, password "
            + "FROM user_info "
            + "WHERE username = :username AND password = :password", User.class);

    nativeQuery.setParameter("username", loginRequest.getUsername());
    nativeQuery.setParameter("password", loginRequest.getPassword());

    return (User) nativeQuery.getSingleResult();
  }
}
