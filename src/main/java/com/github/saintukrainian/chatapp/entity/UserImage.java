package com.github.saintukrainian.chatapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "image_id")
  private Long imageId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "type")
  private String type;

  @Column(name = "name")
  private String name;

  @Lob
  @Type(type = "org.hibernate.type.BinaryType")
  @Column(name = "data")
  private byte[] data;
}
