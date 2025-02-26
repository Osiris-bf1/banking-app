package com.osiris.banking.service.impl;

import com.osiris.banking.dto.TransactionSumDetails;
import com.osiris.banking.entity.TransactionType;
import com.osiris.banking.repository.TransactionRepository;
import com.osiris.banking.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Long userId) {
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
        return transactionRepository.findSumTransactionByDate(start, end, userId);
    }

    @Override
    public BigDecimal getAccountBalance(Long userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Long userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Long userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
