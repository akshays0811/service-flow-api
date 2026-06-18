package com.serviceflow.api.controller;

import com.serviceflow.api.dto.registerdto.RegisterDTO;
import com.serviceflow.api.dto.logindto.LoginRequest;
import com.serviceflow.api.dto.logindto.LoginResponse;
import com.serviceflow.api.service.AuthService;
import com.serviceflow.api.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO){
        authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.phoneNumber(), request.password())
        );

        String jwtToken = authService.login(request.phoneNumber());
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }

}
