package com.chiendang.cooking.api.image.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserImageResponse {
    Long id;
    String imageUrl;
    String cloudImageId;
    String imageName;
}
