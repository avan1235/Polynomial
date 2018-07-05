package org.procyk.text;

/**
 * Created by Maciej Procyk on 14.05.2018.
 */
public class TestTextInput {
    public static void main(String[] args){
        TextInput inputPoly = new TextInput("f(x)=x^2+3x^1-9x^0");
        System.out.println(inputPoly.toStringCoefficients());
    }
}
