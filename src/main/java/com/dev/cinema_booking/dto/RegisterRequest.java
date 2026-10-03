package com.dev.cinema_booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Full name must not be blank")
        @JsonProperty(value = "full_name")
        String fullName ,

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email does not valid")
        String email ,

        @JsonProperty(value = "phone_number")
        @NotBlank(message = "Phone number must not be blank")
        String phoneNumber ,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8 , message = "Password must at least 8 characters length")
        String password
){}
