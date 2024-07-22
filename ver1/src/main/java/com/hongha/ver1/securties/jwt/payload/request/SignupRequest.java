package com.hongha.ver1.securties.jwt.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    private long branchId;

}
