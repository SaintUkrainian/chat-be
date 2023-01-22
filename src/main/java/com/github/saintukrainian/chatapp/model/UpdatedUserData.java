package com.github.saintukrainian.chatapp.model;

import com.github.saintukrainian.chatapp.entity.UserImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class UpdatedUserData extends UpdatedUserName {

  private UserImage userImage;
}
