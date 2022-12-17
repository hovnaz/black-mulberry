package com.black.mulberry.core.util;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.request.UserAuthRequest;
import com.black.mulberry.data.transfer.request.UserUpdateRequest;

public class DataGenerator {

    public static User generateUser(){
        return User.builder()
                .id(1)
                .name("Poghos")
                .surname("Poghosyan")
                .email("test@mail.com")
                .build();
    }

    public static UserUpdateRequest generateUserUpdateRequest(){
        return UserUpdateRequest.builder()
                .name("Yanchka")
                .surname("Poghosyan")
                .build();
    }

    public static UserAuthRequest generateUserAuthRequest(){
        return UserAuthRequest.builder()
                .email("test@mail.com")
                .password("sss444")
                .build();
    }

    public static Product generateProduct(){
        return Product.builder()
                .id(555L)
                .title("table")
                .description("green table")
                .stock(11)
                .build();
    }

    public static ProductRequest generateProductRequest(){
        return ProductRequest.builder()
                .id(555L)
                .title("pencil")
                .description("black pencil")
                .stock(11)
                .build();
    }
}
