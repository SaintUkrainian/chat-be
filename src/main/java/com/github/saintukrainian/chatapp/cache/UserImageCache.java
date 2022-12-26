package com.github.saintukrainian.chatapp.cache;

import com.github.saintukrainian.chatapp.entity.UserImage;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserImageCache {

  private static final Map<Long, UserImage> CACHE = new HashMap<>();

  public static void addToCache(Long userId, UserImage userImage) {
    if (CACHE.containsKey(userId)) {
      CACHE.replace(userId, userImage);
    }
    CACHE.put(userId, userImage);
  }

  public static UserImage getImage(Long userId) {
    return CACHE.get(userId);
  }
}
