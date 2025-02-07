package com.learnjava.arifudinJSB.authapi.controllersauth;

import com.learnjava.arifudinJSB.authapi.modelsauth.Login;
import com.learnjava.arifudinJSB.authapi.dto.LoginUserDto;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.responses.LoginResponse;
import com.learnjava.arifudinJSB.authapi.servicesauth.AuthenticationService;
import com.learnjava.arifudinJSB.authapi.servicesauth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Login> register(@RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(authenticationService.signup(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Login authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expiresAt = System.currentTimeMillis() + jwtService.getJwtExpiration(); // Ambil nilai expiration time

        LoginResponse loginResponse = new LoginResponse(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }


}
