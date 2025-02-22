package com.osiris.banking.repository;

import com.osiris.banking.dto.UserDto;
import com.osiris.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}

