package com.chiendang.cooking.api.auth.mapper;


import com.chiendang.cooking.api.auth.dto.request.UserCreationRequest;
import com.chiendang.cooking.api.auth.dto.request.UserUpdateRequest;
import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import com.chiendang.cooking.api.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    // @MappingTarget cập nhật đối tượng thay vì tạo đối tượng mới
    @Mapping(target = "roles" ,ignore = true) // k gen thuoc tinh roles
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
