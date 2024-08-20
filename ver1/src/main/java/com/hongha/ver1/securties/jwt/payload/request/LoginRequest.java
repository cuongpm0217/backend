package com.hongha.ver1.securties.jwt.payload.request;

import lombok.Getter;

@Getter
public class LoginRequest {

	private String username;
	private String password;
	private long branchId;
	private String ipLogin;
	private String device;
	private String reloadToken;

}
