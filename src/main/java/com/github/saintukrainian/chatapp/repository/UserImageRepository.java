package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Long> {

  boolean existsUserImageByUserId(Long userId);

  void deleteUserImageByUserId(Long userId);
}
