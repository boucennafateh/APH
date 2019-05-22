package utilities;

import java.util.Random;

public class Tools {

    // random number between and max
    public static int getRandom(int max){
        Random rand = new Random();
        int n = rand.nextInt(max);
        return ++n;
    }
}
