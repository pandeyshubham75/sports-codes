import java.util.Arrays;

public class NumbersAtMostNGivenDigitSet {
    public static void main(String[] args) {
//        System.out.println(atMostNGivenDigitSet(new String[]{"1","3","5","7"}, 100));
//        System.out.println(atMostNGivenDigitSet(new String[]{"1","4","9"}, 1000000000));
//        System.out.println(atMostNGivenDigitSet(new String[]{"7"}, 8));
        System.out.println(atMostNGivenDigitSet(new String[]{"3", "5"}, 4));
    }

    public static int atMostNGivenDigitSet(String[] digits, int n) {
        int sol = 0;
        int noOfDigits = 1;
        while (n/(int)Math.pow(10,noOfDigits) > 0) {
            sol+=(int)Math.pow(digits.length, noOfDigits);
            noOfDigits++;
        }
        // now for numbers with same number of digits as n
        Arrays.sort(digits);
        return sol+noOfExactlyNDigitNums(digits, n, noOfDigits);
    }

    static int noOfExactlyNDigitNums (String[] digits, int n, int noOfDigits) {
        if (noOfDigits == 0) return 0;
        int mostSignificantDigit = n/(int)Math.pow(10,--noOfDigits);
        int noOfFullChoices = 0;
        boolean partialChoice = false;
        for (int i = 0; i < digits.length; i++) {
            int currDigit = Integer.parseInt(digits[i]);
            if (mostSignificantDigit > currDigit) noOfFullChoices++;
            else if (mostSignificantDigit == currDigit) partialChoice = true;
            else break;
        }
        int fullOptions = noOfFullChoices * (int)Math.pow(digits.length, noOfDigits);
        return partialChoice ? fullOptions + noOfExactlyNDigitNums(digits, n%(int)Math.pow(10,noOfDigits), noOfDigits) : fullOptions;
    }
}
