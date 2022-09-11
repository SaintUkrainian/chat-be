package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.entity.ChatMessageSimplified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageSimplifiedRepository extends
    JpaRepository<ChatMessageSimplified, Long> {

}
