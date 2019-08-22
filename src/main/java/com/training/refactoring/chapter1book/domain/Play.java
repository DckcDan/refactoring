package com.training.refactoring.chapter1book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Play {

    private String playId;
    private String name;
    private String type;

    public Play() {
    }
}
