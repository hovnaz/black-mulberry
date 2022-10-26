package com.black.mulberry.data.transfer.request.user;

import com.black.mulberry.data.transfer.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserRole role;
    private String password;
}
