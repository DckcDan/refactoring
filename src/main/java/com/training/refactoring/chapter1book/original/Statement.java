package com.training.refactoring.chapter1book.original;

import com.training.refactoring.chapter1book.domain.Invoice;
import com.training.refactoring.chapter1book.domain.Performance;
import com.training.refactoring.chapter1book.domain.Play;
import com.training.refactoring.chapter1book.domain.Plays;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * Statement class from Martin´s refactoring book translated to java.
 *
 * @author Daniel Morales
 */
public class Statement {

    public String issueStatement(Invoice invoice, Plays plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder();
        result.append((String.format("Statement for %s \n", invoice.getCustomer())));
        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.getPlay(perf.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new RuntimeException(String.format("unknown type: %s", play.getType()));
            }

            // add volume credits
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equalsIgnoreCase(play.getType())) volumeCredits += perf.getAudience() / 5;

            // print line for this order

            result.append(String.format("  %s:  %s (%s seats)\n", play.getName(), format.format(thisAmount / 100), perf.getAudience()));
            totalAmount += thisAmount;
        }
        result.append(String.format("Amount owed is %s", format.format(totalAmount / 100)));
        result.append(String.format("You earned %s credits\n", volumeCredits));

        return result.toString();
    }

}
/**
 * Statementfor BigCo
 * Hamlet: $650.00 (55 seats)
 * As You Like It: $580.00 (35 seats)
 * Othello: $500.00 (40 seats)
 * Amount owed is $1,730.00
 * You earned 47 credits
 */

