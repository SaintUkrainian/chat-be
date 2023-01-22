package com.github.saintukrainian.chatapp.repository;

import static com.github.saintukrainian.chatapp.constants.QueryParams.CURRENT_USER_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.FIRST_NAME;
import static com.github.saintukrainian.chatapp.constants.QueryParams.IMAGE_ID;
import static com.github.saintukrainian.chatapp.constants.QueryParams.LAST_NAME;
import static com.github.saintukrainian.chatapp.constants.QueryParams.SEARCH_STRING;
import static com.github.saintukrainian.chatapp.constants.QueryParams.USER_ID;

import com.github.saintukrainian.chatapp.context.UserContext;
import com.github.saintukrainian.chatapp.entity.User;
import com.github.saintukrainian.chatapp.model.UpdatedUserName;
import com.github.saintukrainian.chatapp.model.request.SearchRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
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
            + "WHERE (LOWER(username) LIKE '%' || LOWER(CAST(:searchString AS VARCHAR)) || '%'"
            + "OR LOWER(email) LIKE '%' || LOWER(CAST(:searchString AS VARCHAR)) || '%' "
            + "OR LOWER(first_name) LIKE '%' || LOWER(CAST(:searchString AS VARCHAR)) || '%' "
            + "OR LOWER(last_name) LIKE '%' || LOWER(CAST(:searchString AS VARCHAR)) || '%') "
            + "AND user_id != :currentUserId",
        User.class);

    nativeQuery.setParameter(SEARCH_STRING, searchRequest.getSearchString());
    nativeQuery.setParameter(CURRENT_USER_ID, UserContext.getUserId());

    return nativeQuery.getResultList();
  }

  @Transactional
  public boolean saveImageForUser(Long userId, Long imageId) {
    Query nativeQuery = entityManager.createNativeQuery(
        "UPDATE user_info SET image_id = :imageId WHERE user_id = :userId");

    nativeQuery.setParameter(USER_ID, userId);
    nativeQuery.setParameter(IMAGE_ID, imageId);

    return nativeQuery.executeUpdate() == 1;
  }

  @Transactional
  public boolean updateUserName(Long userId, UpdatedUserName updatedUserName) {
    Query nativeQuery = entityManager.createNativeQuery(
        "UPDATE user_info SET first_name = :firstName, last_name =:lastName "
            + "WHERE user_id = :userId");

    nativeQuery.setParameter(USER_ID, userId);
    nativeQuery.setParameter(FIRST_NAME, updatedUserName.getFirstName());
    nativeQuery.setParameter(LAST_NAME, updatedUserName.getLastName());

    return nativeQuery.executeUpdate() == 1;
  }
}
