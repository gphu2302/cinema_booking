package com.dev.cinema_booking.service;

import com.dev.cinema_booking.core.exception.BusinessException;
import com.dev.cinema_booking.core.exception.ErrorCode;
import com.dev.cinema_booking.dto.RegisterRequest;
import com.dev.cinema_booking.dto.UserDTO;
import com.dev.cinema_booking.entity.User;
import com.dev.cinema_booking.entity.UserRole;
import com.dev.cinema_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO register(RegisterRequest request){
        // check exists email , phone number
        if(userRepository.existsByEmail(request.email()))
             throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);

        if(userRepository.existsByPhoneNumber(request.phoneNumber()))
             throw new BusinessException(ErrorCode.PHONE_ALREADY_EXISTS);

        // Hashing Password -> Create User Entity
        String hashedPass = passwordEncoder.encode(request.password());
        User user = User.builder().fullName(request.fullName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .hashedPassword(hashedPass)
                .role(UserRole.CUSTOMER)
                .build();

        // Repository.save(user)
        User savedUser = userRepository.save(user);

        // Return UserDTO
        return UserDTO.builder().id(savedUser.getId())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .role(savedUser.getRole())
                .fullName(savedUser.getFullName())
                .build();
    }
}
