package com.chiendang.cooking.api.image.service;


import com.chiendang.cooking.api.image.dto.CloudResponse;
import com.chiendang.cooking.api.image.dto.UserImageResponse;
import com.chiendang.cooking.api.image.entity.UserImage;
import com.chiendang.cooking.api.image.mapper.CloudImageMapper;
import com.chiendang.cooking.api.image.mapper.UserImageMapper;
import com.chiendang.cooking.api.image.repository.UserImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserImageServiceImpl implements UserImageService {
    UserImageRepository userImageRepository;
    UserImageMapper mapper;
    CloudinaryService cloudinaryService;
    CloudImageMapper cloudImageMapper;


    @Override
    public UserImage createUserImage(MultipartFile file, Integer userId) throws IOException {
        Map<?,?> res = cloudinaryService.upload(file);
        CloudResponse cloudResponse = cloudImageMapper.toCloudResponse(res);

        return userImageRepository.save(UserImage.builder()
                        .cloudImageId(cloudResponse.getPublicId())
                        .imageUrl(cloudResponse.getUrl())
                        .imageName(cloudResponse.getOriginalFilename())
                        .userId(userId)
                        .build());
    }

    @Override
    public List<UserImageResponse> findAllUserImage(Integer userId) {
        return userImageRepository.findAllByUserId(userId).stream().map(mapper::toUserImageResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteUserImage(Long id) throws IOException {
        UserImage  userImage = userImageRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Khong tim thay user image"));
        cloudinaryService.delete(userImage.getCloudImageId());
        userImageRepository.delete(userImage);
    }
}
