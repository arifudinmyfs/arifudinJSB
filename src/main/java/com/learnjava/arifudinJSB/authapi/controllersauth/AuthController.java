package com.learnjava.arifudinJSB.authapi.controllersauth;

import com.learnjava.arifudinJSB.authapi.dto.LoginRequestDto;
import com.learnjava.arifudinJSB.authapi.dto.TokenResponse;
import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.responses.UserResponse;
import com.learnjava.arifudinJSB.authapi.servicesauth.AuthService;
import com.learnjava.arifudinJSB.authapi.servicesauth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/auth")
public class AuthController {
//    private final JwtService jwtService;

    @Autowired
    private final AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
    private final JwtService jwtService;


    public AuthController(AuthService authService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> authenticate(@RequestBody LoginRequestDto userDto) {
        User authenticatedUser = authService.authenticate(userDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setToken(jwtToken);
        userResponse.setExpiresIn(jwtService.getExpirationTime());

        UserResponse.UserInfo userInfo = new UserResponse.UserInfo();
        userInfo.setUsername(authenticatedUser.getFullName());
        userInfo.setEmail(authenticatedUser.getEmail());
        userResponse.setUser(userInfo);

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Check if the header contains "Bearer"
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid authorization header");
        }

        // Extract token by removing "Bearer " prefix
        String refreshToken = authorizationHeader.replace("Bearer ", "");

        // Call the AuthService to refresh the token
        TokenResponse tokenResponse = authService.refreshAccessToken(refreshToken);

        // Return the new tokens
        return ResponseEntity.ok(tokenResponse);
    }


}
