import flanagan.complex.Complex;
import flanagan.complex.ComplexPoly;
import org.procyk.Interpreter;
import org.procyk.numbers.ComplexRounded;
import org.procyk.numbers.IntegerOperations;
import org.procyk.numbers.Primes;
import org.procyk.text.ProgramString;
import org.procyk.text.TextInput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Maciej Procyk on 14.05.2018.
 */

public class Main {
    public static void main(String[] args){
        Scanner inputKeyboard1 = new Scanner(System.in);

        System.out.println("Polynomial Interpreter");

        char answer = 'i';
        boolean Numbers = false;

        while(!(answer == 'n' ||answer == 'c' )){
            System.out.print("Do you want to use (n)umbers or (c)hars: ");
            answer = inputKeyboard1.next().charAt(0);
        }
        if(answer == 'n'){
            Numbers = true;
        }
        else if(answer == 'c'){
            Numbers = false;
        }

        Scanner inputKeyboard2 = new Scanner(System.in);
        System.out.print("Write your polynomial: ");
        TextInput inputPoly = new TextInput(inputKeyboard2.nextLine());
        ArrayList<Object> coeffs = new ArrayList<>();

        for(int i = 0; i <= inputPoly.getDegree(); i++){
            coeffs.add(i, inputPoly.getCoefficient(i));
        }

        ComplexPoly myPoly = new ComplexPoly(coeffs);

        Complex[] roots = myPoly.roots();
        ComplexRounded[] roundedRoots = new ComplexRounded[roots.length];

        for(int i = 0; i < roots.length; i++){
            roundedRoots[i] = new ComplexRounded(roots[i]);
        }

        LinkedList<Integer> realRoots = new LinkedList<>();
        LinkedList<ComplexRounded> gaussianRoots = new LinkedList<>();
        LinkedList<Integer> imaginaryRoots = new LinkedList<>();

        for (ComplexRounded root : roundedRoots){
            int r = root.getRealPart();
            int i = root.getImaginaryPart();
            if(r != 0 && i != 0){
                if(i > 0){
                    gaussianRoots.add(root);
                }
            }
            else if(r != 0){
                realRoots.add(r);
            }
            else if(i != 0 && i > 0){
                imaginaryRoots.add(i);
            }
        }

        int toBeChecked = gaussianRoots.size()+realRoots.size()+imaginaryRoots.size();

        LinkedList<ComplexRounded> processInOrder = new LinkedList<>();
        Primes primesGenerator = new Primes();

        for(int i = 0; i < toBeChecked; i++){
            int actPrime = primesGenerator.getNthPrime(i+1);

            boolean found = false;

            /**
             * search in gaussian roots
             */
            for(int j = 0; j < gaussianRoots.size(); j++){
                for(int b = 1; b <= 6 && found == false; b++){
                    if(IntegerOperations.power(actPrime, b) == gaussianRoots.get(j).getImaginaryPart()){
                        ComplexRounded temp = new ComplexRounded(gaussianRoots.get(j).getRealPart(), b);
                        processInOrder.add(temp);
                        found = true;
                    }
                }
            }

            /**
             * search in real roots
             */
            if(found == false){
                for(int j = 0; j < realRoots.size(); j++){
                    for(int x = 1; x <= 8 && found == false; x++){
                        if(IntegerOperations.power(actPrime, x) == realRoots.get(j)){
                            ComplexRounded temp = new ComplexRounded(x, 0);
                            processInOrder.add(temp);
                            found = true;
                        }
                    }
                }
            }

            /**
             * search in imaginary roots
             */
            if(found == false){
                for (int j = 0; j < imaginaryRoots.size(); j++){
                    for(int b = 1; b <= 2 && found == false; b++){
                        if(IntegerOperations.power(actPrime, b) == imaginaryRoots.get(j)){
                            ComplexRounded temp = new ComplexRounded(0, b);
                            processInOrder.add(temp);
                            found= true;
                        }
                    }
                }
            }

            if(found == false){
                System.out.println("Error in process of finding prime number " + Integer.toString(actPrime));
            }
        }

        ProgramString programJava = new ProgramString(processInOrder, Numbers);
        try {
            Interpreter.run(programJava.toString());
        }catch (Exception e){
            System.out.println("ERROR: " + e);
        }


    }
}
