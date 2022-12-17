package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuthRequest {

    @Email
    private String email;
    @Size(min = 5, max = 18, message = "password length is between 3-600")
    private String password;
}
