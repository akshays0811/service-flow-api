package com.serviceflow.api.dto.registerdto;

import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {

    @NotBlank(message = "Name is required")
    String name;
    @NotBlank(message = "Phone number is required")
    String phoneNumber;
    @NotBlank(message = "Password is required")
    String password;
    @NotBlank(message = "Confirmed Password is required")
    String cpassword;

}
