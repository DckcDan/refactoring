package com.training.refactoring.chapter1book.refactored;

import com.training.refactoring.chapter1book.domain.Invoice;
import com.training.refactoring.chapter1book.domain.Performance;
import com.training.refactoring.chapter1book.domain.Play;
import com.training.refactoring.chapter1book.domain.Plays;

import java.util.stream.Collectors;

import static java.lang.String.format;


public class StatementProcessor {


    /**
     * Split Phase technique divide the logic into two parts: one that calculates the data required for the statement,
     * the other that renders it into text or HTML.
     * This function tackles the first part.
     */
    public StatementData createStatementData(Invoice invoice, Plays plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances().stream()
                .map(perf -> enrichPerformance(perf, plays))
                .collect(Collectors.toList()));
        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData));
        return statementData;
    }

    private PerformanceData enrichPerformance(Performance performance, Plays plays) {
        PerformanceData perfData = new PerformanceData();
        perfData.setAudience(performance.getAudience());
        perfData.setPlay(playFor(plays, performance));
        perfData.setAmount(amountFor(perfData));
        perfData.setVolumeCredits(volumeCreditsFor(perfData));
        return perfData;
    }

    private int totalAmount(StatementData statementData) {
        return statementData.getPerformances().stream()
                .mapToInt(perf -> amountFor(perf))
                .sum();
    }

    private int totalVolumeCredits(StatementData statementData) {
        return statementData.getPerformances().stream()
                .mapToInt(PerformanceData::getVolumeCredits)
                .sum();
    }

    private int amountFor(PerformanceData perf) {
        int result = 0;
        switch (perf.getPlay().getType()) {
            case "tragedy":
                result = 40000;
                if (perf.getAudience() > 30) {
                    result += 1000 * (perf.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.getAudience() > 20) {
                    result += 10000 + 500 * (perf.getAudience() - 20);
                }
                result += 300 * perf.getAudience();
                break;
            default:
                throw new RuntimeException(format("unknown type: %s", perf.getPlay().getType()));
        }
        return result;

    }


    private int volumeCreditsFor(PerformanceData perf) {
        int result = 0;
        result += Math.max(perf.getAudience() - 30, 0);
        if ("comedy".equalsIgnoreCase(perf.getPlay().getType())) result += perf.getAudience() / 5;
        return result;
    }


    private Play playFor(Plays plays, Performance perf) {
        return plays.getPlay(perf.getPlayID());
    }
}
