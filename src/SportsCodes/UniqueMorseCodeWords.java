package SportsCodes;

public class UniqueMorseCodeWords {
    public static void main(String[] args) {
        System.out.println(uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }

    public static int uniqueMorseRepresentations(String[] words) {
        int size = 0;
        MorseTree morseTree = new MorseTree();
        for (String word : words) if (insertIntoMorseTree(morseTree, word)) size++;
        return size;
    }

    public static boolean insertIntoMorseTree(MorseTree morseTree, String word) {
        String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
                "-.-",".-..","--","-.","---",".--.","--.-",".-.","...",
                "-","..-","...-",".--","-..-","-.--","--.."};
        MorseTree currNode = morseTree;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            String code = codes[c-97];
            for (int j = 0; j < code.length(); j++) {
                char nextChar = code.charAt(j);
                boolean isNextDot = nextChar == '.';
                MorseTree supposedlyNext = isNextDot ? currNode.nextDot : currNode.nextHyphen;
                if (supposedlyNext == null) {
                    supposedlyNext = new MorseTree();
                    supposedlyNext.c = nextChar;
                    if (isNextDot) currNode.nextDot = supposedlyNext; else currNode.nextHyphen = supposedlyNext;
                }
                currNode = supposedlyNext;
            }
        }
        if (currNode.completeCode) return false;
        currNode.completeCode = true;
        return true;
    }
}

class MorseTree {
    char c;
    MorseTree nextDot;
    MorseTree nextHyphen;
    boolean completeCode;
}