package com.osiris.banking.controller;

import com.osiris.banking.dto.TransactionSumDetails;
import com.osiris.banking.service.impl.StatisticsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsServiceImpl statisticsService;

    @GetMapping("/sum-by-date/{userId}")
    public ResponseEntity<List<TransactionSumDetails>> findSumTransactionByDate(
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.findSumTransactionByDate(startDate, endDate, userId));
    }
    
    @GetMapping("/account-balance/{userId}")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.getAccountBalance(userId));
    }

    @GetMapping("/highestTransfert/{userId}")
    public ResponseEntity<BigDecimal> highestTransfert(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.highestTransfert(userId));
    }

    @GetMapping("/highestDeposit/{userId}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.highestDeposit(userId));
    }
}
