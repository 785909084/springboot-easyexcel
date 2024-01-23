package com.al.easyexcel.test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueStringGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int IDENTIFIER_LENGTH = 5;
    private static final int MAX_ATTEMPTS = 1000;

    private Set<String> generatedStrings;
    private Random random;

    public UniqueStringGenerator() {
        generatedStrings = new HashSet<> ();
        random = new Random ();
    }

    public String generateUniqueString() {
        String uniqueString = getRandomString ();

        int attempts = 0;
        while (generatedStrings.contains (uniqueString)) {
            uniqueString = getRandomString ();
            attempts++;

            if (attempts >= MAX_ATTEMPTS) {
                throw new RuntimeException ("无法生成唯一字符串！");
            }
        }

        generatedStrings.add (uniqueString);
        return uniqueString;
    }

    private String getRandomString() {
        StringBuilder stringBuilder = new StringBuilder ();
        for ( int i = 0; i < IDENTIFIER_LENGTH; i++ ) {
            int randomIndex = random.nextInt (CHARACTERS.length ());
            char character = CHARACTERS.charAt (randomIndex);
            stringBuilder.append (character);
        }
        return stringBuilder.toString ();
    }
}


