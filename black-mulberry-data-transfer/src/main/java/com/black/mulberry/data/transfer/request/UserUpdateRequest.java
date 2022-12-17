package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequest {
    @NotBlank(message = "user's name can't be null or empty")
    private String name;
    @NotBlank(message = "user's surname can't be null or empty")
    private String surname;
}
