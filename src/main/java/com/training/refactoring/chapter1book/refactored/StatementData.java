package com.training.refactoring.chapter1book.refactored;

import lombok.Data;

import java.util.List;

@Data
public class StatementData {
    private String customer;
    private List<PerformanceData> performances;
    private int totalAmount;
    private int totalVolumeCredits;
}
