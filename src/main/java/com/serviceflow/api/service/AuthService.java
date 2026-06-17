package com.serviceflow.api.service;

import com.serviceflow.api.dto.registerdto.RegisterDTO;
import com.serviceflow.api.entity.User;
import com.serviceflow.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private BCryptPasswordEncoder passwordEncoder;

    UserRepository userRepository;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterDTO registerDTO) {
        if (userRepository.findByPhoneNumber(registerDTO.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("User already exists with phone number: ******" + registerDTO.getPhoneNumber().substring(6));
        }
        User user = User.builder()
                .name(registerDTO.getName())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .category(registerDTO.getBCategory())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }
}
