package com.training.refactoring.chapter1book.refactored;

import com.training.refactoring.chapter1book.domain.Invoice;
import com.training.refactoring.chapter1book.domain.Performance;
import com.training.refactoring.chapter1book.domain.Play;
import com.training.refactoring.chapter1book.domain.Plays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.lang.String.format;


public class Statement {


    public String statement(Invoice invoice, Plays plays) {
        return renderPlainText(new StatementProcessor().createStatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, Plays plays) {
        return renderHtml(new StatementProcessor().createStatementData(invoice, plays));
    }

    private String renderPlainText(StatementData statementData) {
        StringBuilder result = new StringBuilder();
        result.append(format("Statement for %s \n", statementData.getCustomer()));
        for (PerformanceData perf : statementData.getPerformances()) {
            result.append(format("  %s:  %s (%s seats)\n", perf.getPlay().getName(), usd(perf.getAmount()), perf.getAudience()));
        }
        result.append(format("Amount owed is %s", usd(statementData.getTotalAmount())));
        result.append(format("You earned %s credits\n", statementData.getTotalVolumeCredits()));

        return result.toString();
    }

    private String renderHtml(StatementData statementData) {
        StringBuilder result = new StringBuilder();
        result.append(format("<h1>Statement for %s </h1>\n", statementData.getCustomer()));
        result.append("<table>\n");
        result.append("<tr><th>play</th><th>seats</th><th>cost</th></tr>");
        for (PerformanceData perf : statementData.getPerformances()) {
            result.append("  <tr><td>${perf.play.name}</td><td>${perf.audience}</td>");
            result.append("<td>${usd(perf.amount)}</td></tr>\n");
        }
        result.append("</table>\n");
        result.append("<p>Amount owed is <em>${usd(data.totalAmount)}</em></p>\n");
        result.append("<p>You earned <em>${data.totalVolumeCredits}</em> credits</p>\n");
        return result.toString();
    }


    private String usd(int number) {
        Locale locale = new Locale("en", "US");
        return NumberFormat.getCurrencyInstance(locale).format(number / 100);
    }

}

/**
 * Notes from the book:
 * I like to get rid of variables like play, because temporary variables create a lot of locally scoped names that complicate extractions.
 * The refactoring I will use here is Replace Temp with Query (178). This is for the method called PlaysFor.
 * <p>
 * The great benefit of removing local variables is that it makes it much easier to do extractions, since there is less local scope
 * to deal with. Indeed, usually I’ll take out local variables before I do any extractions.
 * <p>
 * So far, my refactoring has focused on adding enough structure to the function so that I can understand it and see it in terms of
 * its logical parts. This is often the case early in refactoring. Breaking down complicated chunks into small pieces is important, as is naming things well
 **/