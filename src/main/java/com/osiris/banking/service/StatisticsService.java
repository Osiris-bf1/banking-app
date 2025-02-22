package com.osiris.banking.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticsService {

    Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Long userId);

    BigDecimal getAccountBalance(Long userId);

    BigDecimal highestTransfert(Long userId);

    BigDecimal highestDeposit(Long userId);

}
