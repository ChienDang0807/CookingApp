package com.chiendang.cooking.api.image.repository;


import com.chiendang.cooking.api.image.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage,Long> {
    List<UserImage> findAllByUserId(Integer id);
}
