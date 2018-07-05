package org.procyk.numbers;

import flanagan.complex.Complex;

/**
 * Created by Maciej Procyk on 15.05.2018.
 */
public class ComplexRounded {
    private int realPart = 0;
    private int imaginaryPart = 0;

    public ComplexRounded(double real, double imag){
        this.realPart = (int) Math.round(real);
        this.imaginaryPart = (int) Math.round(imag);
    }

    public ComplexRounded(Complex number){
        this.realPart = (int) Math.round(number.getReal());
        this.imaginaryPart = (int) Math.round(number.getImag());
    }

    public int getRealPart(){
        return realPart;
    }

    public int getImaginaryPart() {
        return imaginaryPart;
    }

    public String toString() {
        String out = "";
        if (realPart == 0){
            out = ((imaginaryPart>0)?"":"-") + "j"+Integer.toString(Math.abs(imaginaryPart));
        }
        else if(imaginaryPart == 0){
            out = Integer.toString(realPart);
        }else{
            out = Integer.toString(realPart) + " " + ((imaginaryPart>0)?"+":"-") + " j" + Integer.toString(Math.abs(imaginaryPart));
        }
        return out;
    }
}
