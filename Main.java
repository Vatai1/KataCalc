import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    public static void start(){
        try {
            String instance = scanner.next();//input instance
            char[] instance_char = instance.toCharArray();
            int firstOperand;
            int secondOperand;
            String[] operands = instance.split("[-+*/]");

            if(instance.matches("[0-9IVX]+[-+*/][0-9IVX]+")){

                if (isArabNum(instance)){ //Arabic numbers
                    firstOperand = Integer.parseInt(operands[0]);
                    secondOperand = Integer.parseInt(operands[1]);
                }else { //Rom numbers
                    firstOperand= convertRomToArab(operands[0]);
                    secondOperand = convertRomToArab(operands[1]);
                }

                if((0 <= firstOperand && firstOperand <= 10) && (0 <= secondOperand && secondOperand <= 10)){
                    if (isArabNum(instance)){
                        for (char c : instance_char) { //Calculation for arabic numbers
                            switch (c) {
                                case '+' -> System.out.println(firstOperand + secondOperand);//Plus
                                case '-' -> System.out.println(firstOperand - secondOperand);//Minus
                                case '*' -> System.out.println(firstOperand * secondOperand);//Multiply
                                case '/' -> System.out.println((double) firstOperand / (double) secondOperand);//Division
                            }
                        }
                    }else{
                        for (char c : instance_char) { //Calculation for rom numbers
                            switch (c) {
                                case '+': System.out.println(convertArabToRom(firstOperand+secondOperand));break;//Plus

                                case '-':
                                    if(firstOperand > secondOperand){
                                        System.out.println(convertArabToRom(firstOperand-secondOperand));break;//Minus
                                    }else throw new Exception("There are no negative numbers in the Roman numeral system"); //

                                case '*': System.out.println(convertArabToRom(firstOperand*secondOperand));break;//Multiply
                                case '/': System.out.println(convertArabToRom(firstOperand/secondOperand));break;//Minus
                            }
                        }
                    }
                }else{throw new Exception("Entered number(s) below 0 or above 10");}
            }else{throw new Exception("Not correct instance ");}

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static boolean isArabNum(String instance) throws Exception { //Arab = True; Roman = False; Two systems = Exception
        if(instance.matches("[0-9]+[-+*/][0-9]+")){
            return true;
        }else if(instance.matches("[IVX]+[-+*/][IVX]+")){
            return false;
        }else{throw new Exception("You use 2 numeric systems");}

    }
    public static int convertRomToArab(String romNumber) throws Exception { // X --> 10
        String[] romNumbers = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        boolean result = Arrays.asList(romNumbers).contains(romNumber);
        if(result){
            return Arrays.asList(romNumbers).indexOf(romNumber.toUpperCase())+1;
        }else throw new Exception("Incorrect roman numbers");
    }

    public static String convertArabToRom(int input) {
        String s = "";
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}