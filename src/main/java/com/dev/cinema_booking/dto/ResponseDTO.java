package com.dev.cinema_booking.dto;

import java.time.LocalDateTime;

public record ResponseDTO<E>(
        String message ,
        E data ,
        LocalDateTime timeStamp
){
    public ResponseDTO(String message, E data) {
        this(message, data, LocalDateTime.now());
    }

    public static <E> ResponseDTO<E> success( E data , String message){
        return new ResponseDTO<>(message , data);
    }

    public static <E> ResponseDTO<E> error( String message){
        return new ResponseDTO<>(message , null);
    }
}
