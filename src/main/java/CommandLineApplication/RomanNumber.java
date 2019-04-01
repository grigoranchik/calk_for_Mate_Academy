package CommandLineApplication;

import java.util.TreeMap;

public class RomanNumber {

    private static final char[] LETTERS = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

    private static int decodeSingle(char letter) {
        switch (letter) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    public static int decode(String roman) {
        int result = 0;
        String uRoman = roman.toUpperCase(); //case-insensitive
        for (int i = 0; i < uRoman.length() - 1; i++) {//loop over all but the last character
            if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i + 1))) {
                result -= decodeSingle(uRoman.charAt(i));
            } else {
                result += decodeSingle(uRoman.charAt(i));
            }
        }
        result += decodeSingle(uRoman.charAt(uRoman.length() - 1));
        return result;
    }

    private static boolean loverORequals(char c1, char c2) {
        int indexC1 = 0;
        int indexC2 = 0;
        for (int i = 0; i < LETTERS.length; i++) {
            if (c1 == LETTERS[i])
                indexC1 = i;
            if (c2 == LETTERS[i])
                indexC2 = i;
        }
        if (indexC2 <= indexC1)
            return true;
        else
            return false;
    }

    public static boolean checkNumber(char[] number) {
        boolean flag = true;
        for (int i = 1; i < number.length; i++) {
            if (!loverORequals(number[0], number[i]))
                flag = false;
        }
        return flag;
    }

    public static boolean correctLetters(char[] number) {
        for (int i = 0; i < number.length; i++) {
            boolean flag = false;
            for (int j = 0; j < LETTERS.length; j++) {
                if (number[i] == LETTERS[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}
