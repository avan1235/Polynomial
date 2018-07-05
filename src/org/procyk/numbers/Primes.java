package org.procyk.numbers;

import java.util.LinkedList;

/**
 * Created by Maciej Procyk on 15.05.2018.
 */
public class Primes {
    private int actualSize;
    private static int SIZE_CHANGE = 100;
    private LinkedList<Integer> actSieve = new LinkedList<>();

    public Primes(){
        this.actualSize = SIZE_CHANGE;
        actSieve = Primes.ErastotenesSieve(actualSize);
    }

    private static LinkedList<Integer> ErastotenesSieve(int lastNumber){
        boolean[] isPrime = new boolean[lastNumber];

        if (lastNumber > 2){
            for (int i = 0; i < lastNumber; i++){
                isPrime[i] = true;
            }
            isPrime[0] = false; // 1 is not a prime number

            for (int i = 2; i*i <= lastNumber; i++){
                if(isPrime[i-1] == true){
                    int j = i*i;
                    while (j <= lastNumber){
                        isPrime[j-1] = false;
                        j+=i;
                    }
                }
            }

            LinkedList<Integer> PrimeNumbers = new LinkedList<>();
            for(int i = 0; i < isPrime.length; i++){
                if(isPrime[i] == true){
                    PrimeNumbers.add(i+1);
                }
            }
            return PrimeNumbers;
        }
        else if(lastNumber == 2){
            LinkedList<Integer> numbersTable = new LinkedList<>();
            numbersTable.add(2);
            return numbersTable;
        }
        else {
            return null;
        }
    }

    public int getNthPrime(int N){
        if(N > 0){
            while(true){
                if (N <= actSieve.size()){
                    return actSieve.get(N-1);
                }
                actualSize += SIZE_CHANGE;
                actSieve = Primes.ErastotenesSieve(actualSize);
            }
        }
        else{
            throw new IllegalArgumentException("The value of n is " + N + ". It should be greater than 0");
        }
    }
}
