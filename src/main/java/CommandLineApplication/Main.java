package CommandLineApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> listOfNumbers = new ArrayList<Double>();
        ArrayList<Character> listOfOperations = new ArrayList<Character>();
        СalculationOperations.doCalcProcess(scanner, listOfNumbers, listOfOperations);

    }


}
