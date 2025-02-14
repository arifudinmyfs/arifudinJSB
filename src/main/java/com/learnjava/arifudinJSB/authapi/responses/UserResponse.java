package com.learnjava.arifudinJSB.authapi.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserResponse {
    private String token;
    private long expiresIn;
    private UserInfo user;

    @Getter
    @Setter
    public static class UserInfo {
        private String username;
        private String email;
    }


}
