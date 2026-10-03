package com.dev.cinema_booking.dto;

import com.dev.cinema_booking.entity.UserRole;
import lombok.Builder;

@Builder
public record UserDTO (
        Integer id ,
        String email ,
        String phoneNumber ,
        String fullName ,
        UserRole role
)
{}
