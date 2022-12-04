package com.black.mulberry.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCommentResponse {

    private long id;
    private UserResponse user;
    private String content;
    private LocalDate createAt;
    private LocalDate updateAt;
}
