package com.chiendang.cooking.api.auth.service;


import com.chiendang.cooking.api.auth.dto.request.UserCreationRequest;
import com.chiendang.cooking.api.auth.dto.request.UserUpdateRequest;
import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);

    UserResponse getMyInfo();

    Page<UserResponse> getUsers(Pageable pageable);

    Optional<UserResponse> getUser(Integer id);

    UserResponse updateUser(Integer id, UserUpdateRequest userUpdateRequest);

    void deleteUser (Integer id);

}
