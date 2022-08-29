package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

  List<ChatUser> findChatUsersByUserUserId(Long userId);
  ChatUser findChatUserByUserUserIdAndChatId(Long userId, Long chatId);
}
