package com.chiendang.cooking.api.image.mapper;


import com.chiendang.cooking.api.image.dto.UserImageResponse;
import com.chiendang.cooking.api.image.entity.UserImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserImageMapper {
    UserImageResponse toUserImageResponse(UserImage userImage);
}
