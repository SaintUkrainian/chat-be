package com.github.saintukrainian.chatapp.service;

import static com.github.saintukrainian.chatapp.utils.ImageUtils.compressImage;

import com.github.saintukrainian.chatapp.cache.UserImageCache;
import com.github.saintukrainian.chatapp.entity.UserImage;
import com.github.saintukrainian.chatapp.repository.ComplexUserRepository;
import com.github.saintukrainian.chatapp.repository.UserImageRepository;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserImageService {

  private final UserImageRepository userImageRepository;
  private final ComplexUserRepository complexUserRepository;

  public UserImage saveImageForUser(Long userId, MultipartFile rawImage) throws IOException {
    UserImage imageCompressed = UserImage.builder()
        .type(rawImage.getContentType())
        .name(rawImage.getOriginalFilename())
        .userId(userId)
        .data(compressImage(rawImage.getBytes()))
        .build();

    UserImage savedImage = userImageRepository.save(imageCompressed);

    UserImage imageDecompressed = UserImage.builder()
        .imageId(savedImage.getImageId())
        .type(rawImage.getContentType())
        .name(rawImage.getOriginalFilename())
        .userId(userId)
        .data(rawImage.getBytes())
        .build();

    UserImageCache.addToCache(userId, imageDecompressed);

    complexUserRepository.saveImageForUser(userId, savedImage.getImageId());
    return imageDecompressed;
  }

  public List<UserImage> findAllUserImages() {
    return userImageRepository.findAll();
  }

}
