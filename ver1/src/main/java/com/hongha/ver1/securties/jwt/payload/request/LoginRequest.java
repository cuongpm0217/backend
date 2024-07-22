package com.hongha.ver1.securties.jwt.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String username;
    private String password;
    private long branchId;

}
