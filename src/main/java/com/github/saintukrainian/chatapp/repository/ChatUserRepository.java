package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

}
