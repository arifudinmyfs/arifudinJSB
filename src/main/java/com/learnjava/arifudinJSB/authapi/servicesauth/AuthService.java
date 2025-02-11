package com.learnjava.arifudinJSB.authapi.servicesauth;

import com.learnjava.arifudinJSB.authapi.JwtUtils.JwtUtil;
import com.learnjava.arifudinJSB.authapi.configs.SecurityConfiguration;
import com.learnjava.arifudinJSB.authapi.dto.LoginRequestDto;
import com.learnjava.arifudinJSB.authapi.dto.RefreshTokenRequest;
import com.learnjava.arifudinJSB.authapi.dto.TokenResponse;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import com.learnjava.arifudinJSB.authapi.repositoryauth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private JwtUtil jwtUtils;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //    public User signup(RegisterUserDto input) {
//        return loginRepository.save(new User()
//                .setFullName(input.getFullName())
//                .setEmail(input.getEmail())
//                .setPassword(passwordEncoder.encode(input.getPassword())));
//    }
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

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    // Refresh Access Token Menggunakan Refresh Token
    public TokenResponse refreshAccessToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // Validasi refresh token
        if (jwtUtils.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token kadaluarsa. Silakan login ulang.");
        }

        // Ambil username dari refresh token
        String username = jwtUtils.extractUsername(refreshToken);

        // Generate access token baru
        String newAccessToken = jwtUtils.generateAccessToken(username);

        return new TokenResponse(newAccessToken, refreshToken); // Refresh token tetap sama
    }

}
