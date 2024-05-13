package com.hongha.ver1.securties.jwt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type="honghaauto";
    private Long id;
    private String userName;
    private String email;
    private List<String> roles;
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.userName = username;
        this.email = email;
        this.roles = roles;
    }

}
