package com.serviceflow.api.dto.logindto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Phone number is required")
    String phoneNumber;
    @NotBlank(message = "Password is required")
    String password;
}
