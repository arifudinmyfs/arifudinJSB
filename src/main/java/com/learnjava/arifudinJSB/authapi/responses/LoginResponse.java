package com.learnjava.arifudinJSB.authapi.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
//    private String email;
//    private String fullName;
    private String token;
    private long expiresIn;
    public LoginResponse(String pToken){
        this.token = pToken;
    }

}
