package com.chiendang.cooking.api.image.service;



import com.chiendang.cooking.api.image.dto.UserImageResponse;
import com.chiendang.cooking.api.image.entity.UserImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserImageService {
    UserImage createUserImage(MultipartFile file, Integer userId) throws IOException;
    List<UserImageResponse> findAllUserImage(Integer userId);
    void deleteUserImage(Long id) throws IOException;
}
