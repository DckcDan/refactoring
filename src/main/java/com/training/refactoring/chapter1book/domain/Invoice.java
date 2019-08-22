package com.training.refactoring.chapter1book.domain;

import lombok.Data;

import java.util.List;

@Data
public class Invoice {

    private String customer;
    private List<Performance> performances;

    public Invoice(){ }

}
