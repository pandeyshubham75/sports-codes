public class StringDecode {
    public static void main(String[] args) {
        System.out.println(decodeString("abc2[a]"));
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
        System.out.println(decodeString("abc3[cd]xyz"));
    }

    public static String decodeString(String s) {
        if (s.length() == 0) return "";
        int pos = 0;
        while (pos < s.length() && Character.isLetter(s.charAt(pos))) {
            pos++;
        }
        if (pos == s.length()) return s;
        StringBuffer stringBuffer = new StringBuffer(s.substring(0, pos));
        int nextIntEndPos = pos;
        while (Character.isDigit(s.charAt(++nextIntEndPos)));
        int nextInt = Integer.parseInt(s.substring(pos, nextIntEndPos));



        int lastIndexOfClose = nextIntEndPos+1;
        int openBrackets = 1;
        // finding the closing bracket position
        while (openBrackets > 0) {
            if (s.charAt(lastIndexOfClose) == ']') openBrackets--;
            else if (s.charAt(lastIndexOfClose) == '[') openBrackets++;
            lastIndexOfClose++;
        }

        String nextSegmentDecoded = decodeString(s.substring(nextIntEndPos+1, lastIndexOfClose-1));
        while (nextInt-- > 0) {
            stringBuffer.append(nextSegmentDecoded);
        }
        stringBuffer.append(decodeString(s.substring(lastIndexOfClose)));
        return stringBuffer.toString();
    }
}
