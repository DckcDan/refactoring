package com.training.refactoring.chapter1book.refactored;

import com.training.refactoring.chapter1book.domain.Play;
import lombok.Builder;
import lombok.Data;

@Data
public class PerformanceData {
    private Play play;
    private int audience;
    private int amount;
    private int volumeCredits;

}
