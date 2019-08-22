package com.training.refactoring.chapter1book.original;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;


public final class TestUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T  createObjectFromJsonFile(String jsonFilePath, Class<T> resultClass) throws Exception{
        String invoiceFile = TestUtil.class.getClassLoader().getResource(jsonFilePath).getFile();
        return mapper.readValue(new File(invoiceFile),resultClass);
    }
}