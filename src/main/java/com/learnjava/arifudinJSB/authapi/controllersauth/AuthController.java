package com.learnjava.arifudinJSB.authapi.controllersauth;

import com.learnjava.arifudinJSB.authapi.dto.LoginRequestDto;
import com.learnjava.arifudinJSB.authapi.dto.RefreshTokenRequest;
import com.learnjava.arifudinJSB.authapi.dto.TokenResponse;
import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.responses.UserResponse;
import com.learnjava.arifudinJSB.authapi.servicesauth.AuthService;
import com.learnjava.arifudinJSB.authapi.servicesauth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

        return ResponseEntity.ok(userResponse);
    }

    // Endpoint untuk Refresh Token
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            // Logika normal pemanggilan service untuk refresh token
            TokenResponse response = authService.refreshAccessToken(request);
            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            // Tangkap exception dan kembalikan status 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

        } catch (Exception ex) {
            // Handling untuk error lainnya
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan pada server.");
        }
    }

}
