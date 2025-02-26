package com.osiris.banking.service;

import com.osiris.banking.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Long userId);

    BigDecimal getAccountBalance(Long userId);

    BigDecimal highestTransfert(Long userId);

    BigDecimal highestDeposit(Long userId);

}
