package com.serviceflow.api.controller;

import com.serviceflow.api.dto.registerdto.RegisterDTO;
import com.serviceflow.api.dto.logindto.LoginRequest;
import com.serviceflow.api.dto.logindto.LoginResponse;
import com.serviceflow.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO){
        authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse());
    }

}
