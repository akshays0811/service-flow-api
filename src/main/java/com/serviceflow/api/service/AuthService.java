package com.serviceflow.api.service;

import com.serviceflow.api.dto.registerdto.RegisterDTO;
import com.serviceflow.api.entity.User;
import com.serviceflow.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    UserRepository userRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public void register(RegisterDTO registerDTO) {
        if (userRepository.findByPhoneNumber(registerDTO.phoneNumber()).isPresent()) {
            throw new RuntimeException("User already exists with phone number: ******" + registerDTO.phoneNumber().substring(6));
        }
        User user = User.builder()
                .name(registerDTO.name())
                .password(passwordEncoder.encode(registerDTO.password()))
                .phoneNumber(registerDTO.phoneNumber())
                .category(registerDTO.bCategory())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public String login(String phoneNumber) {
        return jwtService.generateToken(phoneNumber);
    }
}
