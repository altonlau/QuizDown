package com.ag.quizdown;

import java.util.Random;

public class Utils {

    private static Random random = new Random();

    public static int getRandomInt(int maxSize) {
        return random.nextInt(maxSize) + 1;
    }

}
