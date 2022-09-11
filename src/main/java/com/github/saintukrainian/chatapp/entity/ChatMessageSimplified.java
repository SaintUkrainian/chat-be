package com.github.saintukrainian.chatapp.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageSimplified {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "message_id")
  private Long messageId;

  @Column(name = "value")
  private String value;

  @Column(name = "send_timestamp")
  private LocalDateTime sendTimestamp;

  @Column(name = "is_edited")
  private boolean edited;

  @Column(name = "chat_id")
  private Long chatId;

  @Column(name = "from_user_id")
  private Long fromUserId;
}
