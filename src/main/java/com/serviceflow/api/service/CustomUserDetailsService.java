package com.serviceflow.api.service;

import com.serviceflow.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getPhoneNumber(),
                        user.getPassword(),
                        new ArrayList<>() // Empty list signifies basic authenticated user with no special roles
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: ******" + phoneNumber.substring(6)));
    }
}
