package com.learnjava.arifudinJSB.authapi.servicesauth;

import com.learnjava.arifudinJSB.authapi.dto.LoginUserDto;
import com.learnjava.arifudinJSB.authapi.dto.RegisterUserDto;
import com.learnjava.arifudinJSB.authapi.modelsauth.Login;
import com.learnjava.arifudinJSB.authapi.repositoryauth.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

//    public Login signup(RegisterUserDto input) {
//        return loginRepository.save(new Login()
//                .setFullName(input.getFullName())
//                .setEmail(input.getEmail())
//                .setPassword(passwordEncoder.encode(input.getPassword())));
//    }
public Login signup(RegisterUserDto input) {
    Login user = new Login();
    user.setFullName(input.getFullName());
    user.setEmail(input.getEmail());
    user.setPassword(passwordEncoder.encode(input.getPassword()));

    return loginRepository.save(user);
}

    public Login authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

        return loginRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
