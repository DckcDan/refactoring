package com.training.refactoring.chapter1book.domain;

import lombok.Data;

import java.util.List;

@Data
public class Plays {

    private List<Play> plays;

    public Play getPlay(String playID) {
        return plays.stream().filter(f -> f.getPlayId().equalsIgnoreCase(playID))
                .findFirst().orElseThrow(() -> new RuntimeException("Playid not found "+ playID));
    }

    public Plays(){}
}
