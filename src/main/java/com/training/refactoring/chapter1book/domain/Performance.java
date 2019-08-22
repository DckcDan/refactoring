package com.training.refactoring.chapter1book.domain;

import lombok.Data;

@Data
public class Performance {
    private String playID;
    private int audience;

    public Performance() {
    }
}
