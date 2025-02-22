package com.osiris.banking.service.impl;

import com.osiris.banking.dto.TransactionDto;
import com.osiris.banking.entity.Transaction;
import com.osiris.banking.entity.TransactionType;
import com.osiris.banking.entity.User;
import com.osiris.banking.repository.TransactionRepository;
import com.osiris.banking.repository.UserRepository;
import com.osiris.banking.service.TransactionService;
import com.osiris.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public Long save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        transaction.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()))));
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Long id) {
        return transactionRepository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune transaction retrouvée à partir de l'ID fournit "+id));
    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    private int getTransactionMultiplier(TransactionType type) {
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Long userId) {
        return transactionRepository.findByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
