package CommandLineApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class СalculationOperations {

    private static Boolean typeArabicNumbers;

    public static void setTypeNumbersParam(Scanner scanner){
        System.out.println("Please check type of numbers(roman/arabic)");
        String str = scanner.next();
        if(str.equals("arabic")){
            typeArabicNumbers = true;
        } else{
            if(str.equals("roman")){
                typeArabicNumbers = false;
            } else {
                if(str.equals("exit")){
                    return;
                } else{
                    setTypeNumbersParam(scanner);
                }
            }
        }
    }

    public static void doCalcProcess(Scanner scanner, ArrayList<Double> listOfNumbers, ArrayList<Character> listOfOperations){
        String str;

        setTypeNumbersParam(scanner);
        listOfNumbers.add(getNumber(scanner, listOfOperations, listOfNumbers));
        while (true) {
            if (getOperation(scanner, listOfNumbers, listOfOperations) == false) {
                break;
            }
            listOfNumbers.add(getNumber(scanner, listOfOperations, listOfNumbers));
        }
        System.out.println("Do you want to continue calculations(yes/no)?");
        str = scanner.next();
        if(str.equals("yes")){
            listOfNumbers.clear();
            listOfOperations.clear();
            doCalcProcess(scanner, listOfNumbers, listOfOperations);
        } else{
            return;
        }
    }


    private static Double getNumber(Scanner scanner, ArrayList<Character> listOfOperations, ArrayList<Double> listOfNumbers) {
        Double num = null;
        Integer number;

        System.out.println("Input a number: ");
        if(typeArabicNumbers == true){
            num = getNumberArabic(scanner, listOfOperations, listOfNumbers);
        } else{
            num = getNumberRoman(scanner, listOfOperations, listOfNumbers);
        }

        return num;
    }

    private static Double getNumberArabic(Scanner scanner, ArrayList<Character> listOfOperations, ArrayList<Double> listOfNumbers) {
        Double num = null;
        Integer number;

        if (scanner.hasNextInt()) {
            num = (double) scanner.nextInt();
            if (listOfNumbers.size() >=1 && listOfOperations.get(listOfOperations.size() - 1) == '/' && num  == 0 ){
                System.out.println("Error: there will be division by zero");
                num = getNumber(scanner, listOfOperations, listOfNumbers);
            }
        } else {
            System.out.println("Invalid input, enter integer:");
            scanner.next();
            num = getNumber(scanner, listOfOperations, listOfNumbers);
        }


        return num;
    }

    private static Double getNumberRoman(Scanner scanner, ArrayList<Character> listOfOperations, ArrayList<Double> listOfNumbers) {
        Double num = null;
        String number = null;
        number = correctNumberRoman(scanner);
        num = (double) RomanNumber.decode(number);
        if (listOfNumbers.size() >=1 && listOfOperations.get(listOfOperations.size() - 1) == '/' && num  == 0 ){
            System.out.println("Error: there will be division by zero");
            num = getNumber(scanner, listOfOperations, listOfNumbers);
        }
        return num;
    }

    private static String correctNumberRoman(Scanner scanner){
        String number = scanner.next();
        if(number.equals("exit")){
            System.exit(0);
        }
        if (RomanNumber.correctLetters(number.toCharArray())) {
            if (RomanNumber.checkNumber(number.toCharArray())) {
                //System.out.println("Number is correct:)");
            } else {
                System.out.println("Number is incorrect, please try again or write 'exit' ");
                correctNumberRoman(scanner);
            }
        } else{
            System.out.println("Number is incorrect, please try again or write 'exit' ");
            correctNumberRoman(scanner);
        }
        return number;
    }

    private static Boolean getOperation(Scanner scanner, ArrayList<Double> listOfNumbers, ArrayList<Character> listOfOperations) {
        Boolean continueInput = true;
        System.out.println("Check the operation type(*,/,+,-), or enter 'end' to complete: ");
        String str = scanner.next();

        if (str.equals("end")) {
            Double returnRes = returnResult(listOfNumbers, listOfOperations);
            System.out.println("Result in decimal: " + returnRes);
            if(typeArabicNumbers == false){
                if(returnRes <= 0){
                    System.out.println("Result in roman: roman numbers cannot be negative or zero");
                } else{
                    System.out.println("Result in roman: " + RomanNumber.toRoman((int)(double)returnResult(listOfNumbers, listOfOperations)));
                }
            }
            continueInput = false;
            return continueInput;
        } else {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                listOfOperations.add(str.charAt(0));
            } else {
                System.out.println("Invalid input");
                continueInput = getOperation(scanner, listOfNumbers, listOfOperations);
            }
        }

        return continueInput;
    }

    private static Double calc(Double num1, Double num2, Character operation) {
        Double res = null;

        switch (operation) {
            case '+':
                res = (double) num1 + num2;
                break;
            case '-':
                res = (double) num1 - num2;
                break;
            case '*':
                res = (double) num1 * num2;
                break;
            case '/':
                res = (double) num1 / num2;
                break;
        }

        return res;
    }

    private static Double returnResult(ArrayList<Double> listOfNumbers, ArrayList<Character> listOfOperations) {
        Double result = listOfNumbers.get(0);
        for (int i = 0; i < listOfNumbers.size(); i++) {
            if (i + 1 == listOfNumbers.size()) {
                return result;
            }
            result = calc(result, listOfNumbers.get(i + 1), listOfOperations.get(i));

        }


        return result;
    }
}
