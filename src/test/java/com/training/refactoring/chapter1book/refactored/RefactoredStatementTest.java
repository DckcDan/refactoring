package com.training.refactoring.chapter1book.refactored;

import com.training.refactoring.chapter1book.domain.Invoice;
import com.training.refactoring.chapter1book.domain.Plays;
import com.training.refactoring.chapter1book.original.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RefactoredStatementTest {

    private static Invoice invoice;
    private static Plays plays;

    private Statement statement = new Statement();

    @BeforeClass
    public static void setUp() throws Exception {
        invoice = TestUtil.createObjectFromJsonFile("invoice.json", Invoice.class);
        plays = TestUtil.createObjectFromJsonFile("plays.json", Plays.class);

    }


    @Test
    public void produceStatement() {
        String expected = "Statement for BigCo \n" +
                "  Hamlet:  $650.00 (55 seats)\n" +
                "  As You Like It:  $580.00 (35 seats)\n" +
                "  Othello:  $500.00 (40 seats)\n" +
                "Amount owed is $1,730.00You earned 47 credits\n";
        String statement = this.statement.statement(invoice, plays);

        assertThat(statement).isEqualTo(expected);
    }

}