package com.github.saintukrainian.chatapp.model;


import com.github.saintukrainian.chatapp.entity.UserImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserWithImageDto extends UserDto {

  private UserImage userImage;
}

