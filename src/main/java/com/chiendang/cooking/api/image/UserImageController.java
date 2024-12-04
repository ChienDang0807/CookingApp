package com.chiendang.cooking.api.image;


import com.chiendang.cooking.api.auth.dto.response.ResponseData;
import com.chiendang.cooking.api.image.service.UserImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user-image")
@Tag(name = "User Image Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserImageController {
    UserImageService userImageService;

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<?> createUserImage(@RequestParam(value = "file") MultipartFile file,
                                           @RequestParam(value = "id") Integer userId) throws IOException {
        return new ResponseData<>(HttpStatus.CREATED.value(), "Them anh thanh cong",userImageService.createUserImage(file,userId));
    }
}
