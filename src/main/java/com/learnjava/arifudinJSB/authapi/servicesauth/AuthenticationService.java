package com.learnjava.arifudinJSB.authapi.servicesauth;

import com.learnjava.arifudinJSB.authapi.dto.UserDto;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import com.learnjava.arifudinJSB.authapi.repositoryauth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
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

    public User authenticate(UserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
