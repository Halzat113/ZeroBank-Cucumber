package com.zerobank.utilities;


import java.util.List;
import java.util.Random;

public class DataUtils {

    public static int generateSingleNum(int min, int max){
        Random random = new Random();
        max++;
        return random.nextInt(max - min) + min;
    }
}
