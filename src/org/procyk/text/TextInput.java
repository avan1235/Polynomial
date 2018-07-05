package org.procyk.text;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Maciej Procyk on 14.05.2018.
 */

public class TextInput {
    private String inputText;
    private String searchingVariable;
    private int degree;
    private BigInteger[] coefficients;

    /**
     * cannot be a constant function like f(x) = 4
     * @param text
     */

    public TextInput(String text) {
        this.inputText = text.replaceAll("\\s", ""); // delete all spaces from input
        searchingVariable = inputText.substring(inputText.indexOf("(") + 1, inputText.indexOf("(") + 2);

        String workingCopy = inputText.substring(inputText.indexOf("("+searchingVariable+(")"))+3);
        LinkedList<String> parts = new LinkedList<>();

        while(workingCopy.length() > 0 && !workingCopy.equals("=")){
            int actIndex = Math.max(workingCopy.lastIndexOf("+"), workingCopy.lastIndexOf("-"));
            if (actIndex < 0){
                actIndex = workingCopy.lastIndexOf("=")+1;
            }
            String part = workingCopy.substring(actIndex, workingCopy.length());
            parts.add(part);
            workingCopy = workingCopy.substring(0, actIndex);
        }

        String biggest = parts.get(parts.size()-1);
        int xPosition = biggest.indexOf(searchingVariable);
        if(biggest.indexOf("^") >= 0){
            degree = Integer.parseInt(biggest.substring(xPosition+2, biggest.length()));
        }
        else {
            degree = 1;
        }

        coefficients = new BigInteger[degree+1];
        for(int i = 0; i <= degree; i++){
            coefficients[i] = new BigInteger("0");
        }

        for(int i = parts.size()-1; i >= 0; i--){
            String actPart = parts.get(i);
            xPosition = actPart.indexOf(searchingVariable);
            String actCoeff = "";
            String actDegree = "";
            if(xPosition < 0){ // there is no x
                actDegree  = "0";
                actCoeff = actPart;
            }
            else{
                if(actPart.indexOf("^") >= 0){
                    actCoeff = actPart.substring(0, xPosition);
                    actDegree = actPart.substring(actPart.indexOf("^")+1, actPart.length());
                }
                else{
                    actDegree = "1";
                    actCoeff = actPart.substring(0, xPosition);
                }
                if (actCoeff.equals("+") || actCoeff.equals("")){
                    actCoeff = "+1";
                }
                else if(actCoeff.equals("-")){
                    actCoeff = "-1";
                }
            }

            coefficients[Integer.parseInt(actDegree)] = new BigInteger(actCoeff);
        }
    }

    public int getDegree(){
        return degree;
    }

    public String toStringCoefficients(){
        return Arrays.toString(coefficients);
    }

    public BigInteger[] getCoefficients(){
        return coefficients;
    }

    public BigInteger getCoefficient(int index){
        return coefficients[index];
    }

    public String getInputText(){
        return inputText;
    }
}
