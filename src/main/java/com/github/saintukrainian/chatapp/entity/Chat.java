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
@Table(name = "chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chatId;

  private LocalDateTime createTimestamp;

  @ManyToOne
  @JoinColumn(name = "created_by_user_id")
  private ChatUser createdByUser;

  @ManyToOne
  @JoinColumn(name = "to_user_id")
  private ChatUser toUser;
}
