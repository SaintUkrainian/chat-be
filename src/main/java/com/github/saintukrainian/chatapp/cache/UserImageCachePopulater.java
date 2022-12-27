package com.github.saintukrainian.chatapp.cache;

import static com.github.saintukrainian.chatapp.utils.ImageUtils.decompress;

import com.github.saintukrainian.chatapp.entity.UserImage;
import com.github.saintukrainian.chatapp.service.UserImageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserImageCachePopulater {

  private final UserImageService userImageService;

  @EventListener(ApplicationReadyEvent.class)
  public void populateUserImageCache() {
    log.info("Populating user image cache");
    List<UserImage> decompressedImages = userImageService.findAllUserImages().stream()
        .peek(img -> img.setData(decompress(img.getData())))
        .toList();
    decompressedImages.forEach(img -> UserImageCache.addToCache(img.getUserId(), img));
    log.info("Image cache has been populated");
  }

}
