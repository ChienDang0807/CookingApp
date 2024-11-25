package com.chiendang.cooking.api.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    Integer id;
    String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dob;
    String firstName;
    String lastName;
    Set<RoleResponse> roles;

    public UserResponse(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
