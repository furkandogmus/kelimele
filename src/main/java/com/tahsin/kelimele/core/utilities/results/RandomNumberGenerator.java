package com.tahsin.kelimele.core.utilities.results;

import java.util.Random;

public class RandomNumberGenerator {
    public static int getNumber(int upperLimit){
        Random random = new Random();
        return random.nextInt(upperLimit);
    }


}
