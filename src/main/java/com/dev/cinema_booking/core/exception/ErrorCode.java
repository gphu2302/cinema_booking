package com.dev.cinema_booking.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Email already exists in the system"),
    PHONE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Phone number already exists in the system"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid email or password"),

    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized error");

    private final HttpStatus status;
    private final String message;
}
