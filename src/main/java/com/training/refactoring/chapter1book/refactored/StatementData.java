package com.training.refactoring.chapter1book.refactored;

import com.training.refactoring.chapter1book.domain.Performance;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Daniel Morales
 */
@Data
public class StatementData {
    private String customer;
    private List<PerformanceData> performances;
    private int totalAmount;
    private int totalVolumeCredits;
}
