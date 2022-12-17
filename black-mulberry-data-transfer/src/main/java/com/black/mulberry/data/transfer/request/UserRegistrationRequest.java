package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationRequest {

    @NotBlank(message = "user's name can't be null or empty")
    private String name;
    @NotBlank(message = "user's surname can't be null or empty")
    private String surname;
    @Email
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})")
    @NotBlank
    private String phone;
    @Size(min = 5, max = 18, message = "password length is between 3-18")
    private String password;
}
