package com.github.saintukrainian.chatapp.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long messageId;

  private Long chatId;

  private String value;

  private Long fromUserId;

  private Long toUserId;

  private LocalDateTime sendTimestamp;

  @ManyToOne
  @JoinColumn(name = "chatId")
  private Chat chat;

  @ManyToOne
  @JoinColumn(name = "from_user_id")
  private ChatUser fromUser;

  @ManyToOne
  @JoinColumn(name = "to_user_id")
  private ChatUser toUser;
}
