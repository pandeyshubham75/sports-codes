package SportsCodes;

import java.util.HashMap;
import java.util.Map;

public class MinimumLengthEncoding {
    public static void main(String[] args) {
        MinimumLengthEncoding i = new MinimumLengthEncoding();
        System.out.println(i.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        System.out.println(i.minimumLengthEncoding(new String[]{"p","grah","qwosp"}));
        System.out.println(i.minimumLengthEncoding(new String[]{"t"}));
        System.out.println(i.minimumLengthEncoding(new String[]{"time", "atime", "btime"}));
        System.out.println(i.minimumLengthEncoding2(new String[]{"time", "me", "bell"}));
        System.out.println(i.minimumLengthEncoding2(new String[]{"p","grah","qwosp"}));
        System.out.println(i.minimumLengthEncoding2(new String[]{"t"}));
        System.out.println(i.minimumLengthEncoding2(new String[]{"time", "atime", "btime"}));
    }

    public int minimumLengthEncoding(String[] words) {
        Map<String, MapValueNode> wordIndices = new HashMap<>();
        StringBuilder out = new StringBuilder();
        int currIndex = 0;
        String tmpWord;
        boolean matched;
        for (String word: words){
            if (wordIndices.containsKey(word)){
                continue;
            }
            matched = false;
            for (int i = 1; i < word.length(); i++) {
                tmpWord = word.substring(i);
                if (wordIndices.containsKey(tmpWord)){
                    MapValueNode node = wordIndices.get(tmpWord);
                    if (node.startOfWord){
                        out.insert(node.index, word.substring(0, i));
                        // update map entries
                        wordIndices.put(word, new MapValueNode(node.index+1, true));
                        for (int j = 1; j < i; j++){
                            wordIndices.put(word.substring(j), new MapValueNode(node.index+j+1, false));
                        }
                        matched = true;
                        node.startOfWord = false;
                        break;
                    }
                }
            }
            if (!matched){
                out.append(word);
                out.append("#");
                wordIndices.put(word, new MapValueNode(currIndex++, true));
                for (int i = 1; i < word.length(); i++){
                    wordIndices.put(word.substring(i), new MapValueNode(currIndex++, false));
                }
            }
        }
//        System.out.println(out);
        return out.length();
    }

    public int minimumLengthEncoding2(String[] words) {
        MyTrieNode trie = new MyTrieNode(null, false);
        int count = 0;
        int locCount;
        MyTrieNode child;
        char c;
        boolean diverged;
        for (String word : words){
            locCount = 1;
            child = trie;
            diverged = false;
            for (int i = word.length()-1; i > -1; i--){
                c = word.charAt(i);
                if (child.children == null) child.children = new HashMap<>();
                if (!child.children.containsKey(c)) {
                    diverged = true;
                    if (child.word) {
                        locCount = 0;
                        child.word = false;
                    }
                    child.children.put(c, new MyTrieNode(c, i==0));
                }
                child = child.children.get(c);
                locCount++;
            }
            if (diverged) {
                count+=locCount;
                child.word = true;
            }
        }
        return count;
    }
}

class MapValueNode {
    int index;
    boolean startOfWord;
    MapValueNode(int index, boolean startOfWord) {
        this.index = index;
        this.startOfWord = startOfWord;
    }
}

class MyTrieNode {
    Character character;
    Map<Character, MyTrieNode> children;
    boolean word;

    MyTrieNode(Character character, boolean word) {
        this.character = character;
        this.word = word;
    }
}

