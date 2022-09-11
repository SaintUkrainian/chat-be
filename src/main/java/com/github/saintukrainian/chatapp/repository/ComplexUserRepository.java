package com.github.saintukrainian.chatapp.repository;

import static com.github.saintukrainian.chatapp.constants.QueryParams.SEARCH_STRING;

import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComplexUserRepository {

  private final EntityManager entityManager;

  public List<User> findUsersBySearchRequest(SearchRequest searchRequest) {
    log.info("Finding users by search request: {}", searchRequest);
    Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM user_info "
            + "WHERE LOWER(username) LIKE '%' || LOWER(CAST(:searchString AS VARCHAR)) || '%'"
            + "OR LOWER(email) LIKE '%' || LOWER(CAST(:searchString AS VARCHAR)) || '%'", User.class);

    nativeQuery.setParameter(SEARCH_STRING, searchRequest.getSearchString());

    return (List<User>) nativeQuery.getResultList();
  }
}