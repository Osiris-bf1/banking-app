package com.osiris.banking.service.impl;

import com.osiris.banking.dto.AccountDto;
import com.osiris.banking.entity.Account;
import com.osiris.banking.entity.User;
import com.osiris.banking.exceptions.OperationNonePermittedException;
import com.osiris.banking.repository.AccountRepository;
import com.osiris.banking.service.AccountService;
import com.osiris.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Long save(AccountDto dto) {
//        if (dto.getId() != null) {
//            throw new OperationNonePermittedException(
//                    "Le compte ne peut pas être modifié",
//                    "save account",
//                    "Account",
//                    "Mise à jour non permise"
//            );
//        }
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();

        if (userHasAlreadyAnAccount) {
            throw new OperationNonePermittedException(
                    "Cet utilisateur a déjà un compte",
                    "create account",
                    "Account service",
                    "Account creation"
            );
        }

        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
        }

        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Long id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun compte retrouvé avec l'ID fournit "+id));
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    private String generateRandomIban() {
        // génère un iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        // vérifie si ce iban exist
        boolean existIban = accountRepository.findByIban(iban).isPresent();

        // si oui regenere un nouveau iban
        if (existIban) {
            generateRandomIban();
        }

        // si non retourne le iban genere
        return iban;
    }
}
