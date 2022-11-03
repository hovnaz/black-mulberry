package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    private String name;
    private String surname;
    private String email;
    private String phone;
}
