package SportsCodes;

import java.util.HashMap;

public class RomanToInt {
    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        int num = 0;
        char c;
        int l = s.length();
        for (int i = 0; i < l-1; i++){
            c = s.charAt(i);
            if ((c == 'I' && (s.charAt(i+1) == 'V' || s.charAt(i+1) == 'X'))
                    || (c == 'X' && (s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C'))
                || (c == 'C' && (s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M'))){
                num -= numForChar(c);
                continue;
            }
            num += numForChar(c);
        }
        num += numForChar(s.charAt(l-1));
        return num;
    }

    static int numForChar(char c) {
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }
}
