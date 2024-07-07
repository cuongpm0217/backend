package com.hongha.ver1.securties.jwt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type="honghaauto";
    private UUID genId;
    private String userName;
    private String email;
    private List<String> roles;
    public JwtResponse(String accessToken, UUID genId, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.genId = genId;
        this.userName = username;
        this.email = email;
        this.roles = roles;
    }

}
