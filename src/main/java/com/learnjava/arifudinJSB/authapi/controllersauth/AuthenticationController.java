package com.learnjava.arifudinJSB.authapi.controllersauth;

import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import com.learnjava.arifudinJSB.authapi.dto.UserDto;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.responses.UserResponse;
import com.learnjava.arifudinJSB.authapi.servicesauth.AuthenticationService;
import com.learnjava.arifudinJSB.authapi.servicesauth.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> authenticate(@RequestBody UserDto userDto) {
        User authenticatedUser = authenticationService.authenticate(userDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setToken(jwtToken);
        userResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(userResponse);
    }
}
