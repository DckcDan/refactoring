package com.training.refactoring.chapter1book.refactored;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum EventType{

    COMEDY("comedy"),
    TRAGEDY("tragedy");

    private static List<EventType> values = Arrays.asList(EventType.values());

    private String code;


     EventType(String code){
         this.code = code;
    }

     public static EventType getEventType(String code){
       return  values.stream()
                 .filter(event -> event.getCode().equalsIgnoreCase(code))
                 .findAny()
                 .orElseThrow(()->new IllegalArgumentException("No enum constant for "+ code));
     }
}
