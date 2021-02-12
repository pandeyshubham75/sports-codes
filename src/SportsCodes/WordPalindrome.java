package SportsCodes;

public class WordPalindrome {
    public static void main(String[] args) {
        WordPalindrome i = new WordPalindrome();
        System.out.println(i.isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        int len = s.length() - 1;
        if (len < 1) return true;
        int left = 0;
        while (left < len) {
            //move l & r to next valid
            while (left < len && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < len && !Character.isLetterOrDigit(s.charAt(len))) len--;
//            System.out.println(left); System.out.println(len);
            if (left < len && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(len))) return false;
            left++;
            len--;
        }
        return true;
    }
}
