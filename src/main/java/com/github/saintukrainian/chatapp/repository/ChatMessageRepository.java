package com.github.saintukrainian.chatapp.repository;

import com.github.saintukrainian.chatapp.entity.ChatMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

  List<ChatMessage> findChatMessagesByChatChatIdOrderBySendTimestampAsc(Long chatId);

  ChatMessage findTopByChatChatIdOrderBySendTimestampDesc(Long chatId);
}
