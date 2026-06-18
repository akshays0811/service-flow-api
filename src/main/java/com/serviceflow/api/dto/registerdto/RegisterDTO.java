package com.serviceflow.api.dto.registerdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO (
    @NotBlank(message = "Name is required")
    String name,
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number should be length of 10")
    String phoneNumber,
    @NotBlank(message = "Password is required")
    String password,
    @NotBlank(message = "Business Category is required")
    String bCategory
) {}
