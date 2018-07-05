package org.procyk.numbers;

/**
 * Created by Maciej Procyk on 15.05.2018.
 */
public class IntegerOperations {
    public static int power(int a, int b){
        int result = 1;
        if(b >= 0){
            int actMuliplay = a;
            int actPower = b;
            while(actPower != 0){
                if(actPower%2 == 1){
                    result *= actMuliplay;
                }
                actMuliplay *= actMuliplay;
                actPower /= 2;
            }
            return result;
        }
        else {
            throw new NumberFormatException("b has to be >= 0");
        }
    }
}
