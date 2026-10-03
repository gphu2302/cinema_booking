package com.dev.cinema_booking.controller;

import com.dev.cinema_booking.dto.RegisterRequest;
import com.dev.cinema_booking.dto.ResponseDTO;
import com.dev.cinema_booking.dto.UserDTO;
import com.dev.cinema_booking.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserDTO>> register(@Valid @RequestBody RegisterRequest request){
        UserDTO userDTO = authService.register(request);
        ResponseDTO<UserDTO> response = ResponseDTO.success(userDTO , "User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
