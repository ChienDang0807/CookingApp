package com.chiendang.cooking.api.image.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserImage {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    @SequenceGenerator(name = "image_seq", sequenceName = "image_seq", allocationSize = 1)
    Long id;
    String imageUrl;
    String cloudImageId;
    String imageName;
    Integer userId;
}
