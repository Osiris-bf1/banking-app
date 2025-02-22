package com.osiris.banking.repository;

import com.osiris.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIban(String iban);
    Optional<Account> findByUserId(Long userId);
}
