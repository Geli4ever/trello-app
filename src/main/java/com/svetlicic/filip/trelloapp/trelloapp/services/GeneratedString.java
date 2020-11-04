package com.svetlicic.filip.trelloapp.trelloapp.services;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneratedString {
    public static final GeneratedString INSTANCE = new GeneratedString();

    private GeneratedString() {
    }

    public String generateRandomString(){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
