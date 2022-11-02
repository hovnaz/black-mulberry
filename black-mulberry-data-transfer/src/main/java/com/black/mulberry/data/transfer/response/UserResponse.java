package com.black.mulberry.data.transfer.response;

import com.black.mulberry.data.transfer.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserRole role;
    private Date createAt;
}
