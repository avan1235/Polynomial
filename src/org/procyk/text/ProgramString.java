package org.procyk.text;

import org.procyk.numbers.ComplexRounded;
import java.util.LinkedList;

/**
 * Created by Maciej Procyk on 15.05.2018.
 */

public class ProgramString {
    private String value = "";
    private LinkedList<ComplexRounded> processInOrder = new LinkedList<>();

    public ProgramString(LinkedList<ComplexRounded> inOrder, boolean ifNumbers){
        this.processInOrder = (LinkedList<ComplexRounded>) inOrder.clone();
        addBegin(ifNumbers);
        analyze(ifNumbers);
        addEnd();
    }

    private void addBegin(boolean ifNum){
        value += "import java.util.Scanner;" +
                 "public class Interpreter {" +
                 "public static void main(String[] args) {" +
                 (ifNum?"int out;":"char out;") +
                 "Scanner in;" +
                 "int result;" +
                 "int REGISTER = 0;";
    }

    private void addEnd(){
        value += "}" +
                 "}";
    }

    private void analyze(boolean ifNum){
        for(ComplexRounded root : processInOrder){
            int r = root.getRealPart();
            int i = root.getImaginaryPart();

            if(r!=0 && i!=0){
                String a = Integer.toString(r);
                switch (i){
                    case 1:{
                        value += "REGISTER += " + a + ";";
                    }break;
                    case 2:{
                        value += "REGISTER -= " + a + ";";
                    }break;
                    case 3:{
                        value += "REGISTER *= " + a + ";";
                    }break;
                    case 4:{
                        value += "REGISTER /= " + a + ";";
                    }break;
                    case 5:{
                        value += "REGISTER %= " + a + ";";
                    }break;
                    case 6:{
                        value += "result = 1;" +
                                "for(int i = 1; i <= " + a + "; i++){" +
                                "result *= REGISTER;" +
                                "}" +
                                "REGISTER = result;";
                    }break;
                }
            }
            else if(r!=0){
                switch (r){
                    case 1:{
                        value += "if (REGISTER > 0) {";
                    }break;
                    case 2:{
                        value += "}";
                    }break;
                    case 3:{
                        value += "if (REGISTER < 0) {";
                    }break;
                    case 4:{
                        value += "if (REGISTER == 0) {";
                    }break;
                    case 5:{
                        value += "while (REGISTER > 0) {";
                    }break;
                    case 6:{
                        value += "}";
                    }break;
                    case 7:{
                        value += "while (REGISTER < 0) {";
                    }break;
                    case 8:{
                        value += "while (REGISTER == 0) {";
                    }break;
                }
            }
            else{
                if(i == 1){
                    value += (ifNum?"out = REGISTER;":"out = (char) (REGISTER%256);") +
                             "if((int)out >= 0){" +
                             "System.out.print(out);}";
                }
                else if(i == 2){
                    value += "in = new Scanner(System.in);" +
                            "if(in.hasNextLine()){" +
                            (ifNum?"REGISTER = in.nextInt();":"REGISTER = in.next().charAt(0);") +
                            "}";
                }
            }
        }
    }

    public String toString(){
        return value;
    }
}
