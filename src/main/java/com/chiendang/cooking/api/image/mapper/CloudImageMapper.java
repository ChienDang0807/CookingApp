package com.chiendang.cooking.api.image.mapper;



import com.chiendang.cooking.api.image.dto.CloudResponse;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface CloudImageMapper {

    default CloudResponse toCloudResponse(Map<?, ?> map) {
        CloudResponse fileMetadata = new CloudResponse();
        if (map.containsKey("url")) {
            fileMetadata.setUrl(String.valueOf(map.get("url")));
        }
        if (map.containsKey("public_id")) {
            fileMetadata.setPublicId(String.valueOf(map.get("public_id")));
        }
        if (map.containsKey("original_filename")) {
            fileMetadata.setOriginalFilename(String.valueOf(map.get("original_filename")));
        }
        return fileMetadata;
    }
}
