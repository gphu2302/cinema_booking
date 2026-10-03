package com.dev.cinema_booking.repository;

import com.dev.cinema_booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByEmail(String email);
    public boolean existsByPhoneNumber(String phoneNumber);
}
