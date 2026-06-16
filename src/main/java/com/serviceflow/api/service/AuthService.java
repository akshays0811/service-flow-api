package com.serviceflow.api.service;

import com.serviceflow.api.dto.registerdto.RegisterDTO;
import com.serviceflow.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void register(RegisterDTO registerDTO) {

    }
}
