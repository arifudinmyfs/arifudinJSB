package com.learnjava.arifudinJSB.authapi.servicesauth;

import com.learnjava.arifudinJSB.authapi.dto.LoginRequestDto;
import com.learnjava.arifudinJSB.authapi.dto.TokenResponse;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import com.learnjava.arifudinJSB.authapi.repositoryauth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginRequestDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

        return userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Refresh access token
    // Method to refresh the access token using the refresh token
    public TokenResponse refreshAccessToken(String request) {
        String refreshToken = request;

        // Validasi refresh token
        if (jwtService.isTokenValid(request)) {
            throw new RuntimeException("Refresh token kadaluarsa. Silakan login ulang.");
        }

        // Ambil username dari refresh token
        String newAccessToken = jwtService.refreshTokenData(refreshToken);
        return new TokenResponse(newAccessToken); // Refresh token tetap sama
    }

}
