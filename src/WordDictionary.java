import java.util.HashMap;
import java.util.Map;

class WordDictionary {

    public static void main(String[] args) {
        /**
         * Your WordDictionary object will be instantiated and called as such:
         */
        WordDictionary obj = new WordDictionary();
        String[] words = new String[]{
                "bad","dad","mad"
        };
        String[] wordsToSearch = new String[]{
                "pad","bad",".ad","b.."
        };
        for (String s : words) obj.addWord(s);
        for (String s : wordsToSearch) System.out.println(s+ " : "+ obj.search(s));
    }

    Triee Triee;

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.Triee = new Triee();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null) return;
        Triee currentNode = this.Triee;
        for (int i = 0; i < word.length(); i++) {
            Character nextChar = word.charAt(i);
            if (currentNode.hasChild(nextChar)) {
                currentNode = currentNode.getChild(nextChar);
                continue;
            }
            Triee nextNode = new Triee(word.charAt(i));
            currentNode.putChild(nextNode);
            currentNode = nextNode;
        }
        currentNode.setWord(true);
    }

    public boolean search(String word) {
        return search(word, this.Triee);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word, Triee triee) {
        if (word == null) return false;
        for (int i = 0; i < word.length(); i++) {
            if (triee == null) return false;
            if (triee.children == null) return false;
            if (word.charAt(i) == '.') {
                for (Triee t : triee.children.values()) {
                    if (search(word.substring(i+1), t)) return true;
                }
                return false;
            }
            triee = triee.children.get(word.charAt(i));
            if (triee == null) return false;
        }
        return triee.word;
    }
}

class Triee {
    Character value;
    Map<Character, Triee> children;
    boolean word;

    public Triee() {
    }

    public Triee(Character value) {
        this.value = value;
    }

    public void putChild(Triee child) {
        if (this.children == null) this.children = new HashMap<>();
        this.children.put(child.value, child);
    }

    public Triee getChild(Character c) {
        if (this.children == null) return null;
        return this.children.get(c);
    }

    public boolean hasChild(Character c) {
        if (this.children == null) return false;
        return this.children.get(c) != null;
    }

    public boolean isWord() { return this.word; }
    public void setWord(boolean word) { this.word = word; }

    public void setValue(Character value) { this.value = value; }
    public Character getValue() { return this.value; }
}