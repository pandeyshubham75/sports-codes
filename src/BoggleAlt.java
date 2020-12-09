/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.


[["b","a","a","b","a","b"],["a","b","a","a","a","a"],["a","b","a","a","a","b"],["a","b","a","b","b","a"],["a","a","b","b","a","b"],["a","a","b","b","b","a"],["a","a","b","a","a","b"]]
["bbaabaabaaaaabaababaaaaababb","aabbaaabaaabaabaaaaaabbaaaba","babaababbbbbbbaabaababaabaaa","bbbaaabaabbaaababababbbbbaaa","babbabbbbaabbabaaaaaabbbaaab","bbbababbbbbbbababbabbbbbabaa","babababbababaabbbbabbbbabbba","abbbbbbaabaaabaaababaabbabba","aabaabababbbbbbababbbababbaa","aabbbbabbaababaaaabababbaaba","ababaababaaabbabbaabbaabbaba","abaabbbaaaaababbbaaaaabbbaab","aabbabaabaabbabababaaabbbaab","baaabaaaabbabaaabaabababaaaa","aaabbabaaaababbabbaabbaabbaa","aaabaaaaabaabbabaabbbbaabaaa","abbaabbaaaabbaababababbaabbb","baabaababbbbaaaabaaabbababbb","aabaababbaababbaaabaabababab","abbaaabbaabaabaabbbbaabbbbbb","aaababaabbaaabbbaaabbabbabab","bbababbbabbbbabbbbabbbbbabaa","abbbaabbbaaababbbababbababba","bbbbbbbabbbababbabaabababaab","aaaababaabbbbabaaaaabaaaaabb","bbaaabbbbabbaaabbaabbabbaaba","aabaabbbbaabaabbabaabababaaa","abbababbbaababaabbababababbb","aabbbabbaaaababbbbabbababbbb","babbbaabababbbbbbbbbaabbabaa"]

 */

import java.util.*;

public class BoggleAlt {
    public  static void main (String ar[]){
        char[][] board1 =
                {
                    {'o','a','a','n'},
                        {'e','t','a','e'},
                                {'i','h','k','r'},
                                        {'i','f','l','v'}
        };
        char[][] board2 ={{'a'}, {'a'}};
                char[][] board3={{'a','b'},{'c','d'}};
                char[][] board4 ={{'b','a','a','b','a','b'},
                 {'a','b','a','a','a','a'},
                 {'a','b','a','a','a','b'},
                 {'a','b','a','b','b','a'},
                 {'a','a','b','b','a','b'},
                 {'a','a','b','b','b','a'},
                 {'a','a','b','a','a','b'}};
        String[] words1 = {"eat", "oath", "pea", "rain"};
        String[] words2 = {"aa"};
        String[] words3 =        {"cdba"};
        String[] words4 =        {"bbaabaabaaaaabaababaaaaababb","aabbaaabaaabaabaaaaaabbaaaba","babaababbbbbbbaabaababaabaaa","bbbaaabaabbaaababababbbbbaaa","babbabbbbaabbabaaaaaabbbaaab","bbbababbbbbbbababbabbbbbabaa","babababbababaabbbbabbbbabbba","abbbbbbaabaaabaaababaabbabba","aabaabababbbbbbababbbababbaa","aabbbbabbaababaaaabababbaaba","ababaababaaabbabbaabbaabbaba","abaabbbaaaaababbbaaaaabbbaab","aabbabaabaabbabababaaabbbaab","baaabaaaabbabaaabaabababaaaa","aaabbabaaaababbabbaabbaabbaa","aaabaaaaabaabbabaabbbbaabaaa","abbaabbaaaabbaababababbaabbb","baabaababbbbaaaabaaabbababbb","aabaababbaababbaaabaabababab","abbaaabbaabaabaabbbbaabbbbbb","aaababaabbaaabbbaaabbabbabab","bbababbbabbbbabbbbabbbbbabaa","abbbaabbbaaababbbababbababba","bbbbbbbabbbababbabaabababaab","aaaababaabbbbabaaaaabaaaaabb","bbaaabbbbabbaaabbaabbabbaaba","aabaabbbbaabaabbabaabababaaa","abbababbbaababaabbababababbb","aabbbabbaaaababbbbabbababbbb","babbbaabababbbbbbbbbaabbabaa"};
        BoggleAlt boggle = new BoggleAlt();
        Long milis = System.currentTimeMillis();
        List<String> matchedWords1 = boggle.findWords(board1, words1);
        List<String> matchedWords2 = boggle.findWords(board2, words2);
        List<String> matchedWords3 = boggle.findWords(board3, words3);
        List<String> matchedWords4 = boggle.findWords(board4, words4);
        for (String str : matchedWords1) System.out.println(str);
        System.out.println();
        for (String str : matchedWords2) System.out.println(str);
        System.out.println();
        for (String str : matchedWords3) System.out.println(str);
        System.out.println();
        for (String str : matchedWords4) System.out.println(str);
        System.out.println();
        System.out.println(System.currentTimeMillis()-milis);
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNodeServiceAlt trieNodeService = new TrieNodeServiceAlt();
        TrieNodeAlt dictionary = trieNodeService.getNewEmptyTrie();
        for (String word : words) trieNodeService.insertIntoTrie(dictionary, word);
        int size = board.length;
        Set<String> matchingWords = new LinkedHashSet<>();
        for (int i = 0; i < size; i++) {
            int vSize = board[i].length;
            for (int j = 0; j < vSize; j++) {
                boolean[][] visitedIndices = new boolean[size][vSize];
//                System.out.println("Starting again from "+i+","+j);
                findMatchingWords(dictionary, i, j, board, visitedIndices, matchingWords, "", size, vSize);
            }
        }
        return new ArrayList<>(matchingWords);
    }

    public void findMatchingWords(TrieNodeAlt trie,
                                  int i, int j, char[][] board,
                                  boolean[][] visitedIndices,
                                  Set<String> matchingWords,
                                  String wordMatchedTillNow,
                                  int size, int vSize) {
        if (i < 0 || i >= size || j < 0 || j >= vSize || visitedIndices[i][j]) {
            return;
        }
        boolean[][] newVisitedIndices = new boolean[size][vSize];
        for (int ii = 0; ii < size; ii++) {
            for (int jj = 0; jj < vSize; jj++) {
                newVisitedIndices[ii][jj] = visitedIndices[ii][jj];
            }
        }
        char currentChar = board[i][j];
        TrieNodeAlt nextNode = trie.children.get(currentChar);
        if (nextNode == null) {
//            System.out.println("Give Up, Nothing on this Side");
            return;
        }
        newVisitedIndices[i][j] = true;
        wordMatchedTillNow+=currentChar;
        if (nextNode.word) {
            matchingWords.add(wordMatchedTillNow);
//            System.out.println("Word Matched "+wordMatchedTillNow);
        }
        if (nextNode.children.size() == 0) {
//            System.out.println("Reached Leaf");
            return;
        }
//        System.out.println("Matched Char: "+currentChar+ " coordinates: " +i+ "," +j+ " Matched till now: " + wordMatchedTillNow);
        findMatchingWords(nextNode, i-1, j, board, newVisitedIndices, matchingWords, wordMatchedTillNow, size, vSize);
        findMatchingWords(nextNode, i+1, j, board, newVisitedIndices, matchingWords, wordMatchedTillNow, size, vSize);
        findMatchingWords(nextNode, i, j-1, board, newVisitedIndices, matchingWords, wordMatchedTillNow, size, vSize);
        findMatchingWords(nextNode, i, j+1, board, newVisitedIndices, matchingWords, wordMatchedTillNow, size, vSize);
    }
}

class TrieNodeAlt {
    Character character;
    Map<Character, TrieNodeAlt> children;
    boolean word;

    TrieNodeAlt(Character character, boolean word) {
        this.character = character;
        this.word = word;
        this.children = new HashMap<>();
    }
    TrieNodeAlt(Character character, boolean word, Map<Character, TrieNodeAlt> children) {
        this.character = character;
        this.word = word;
        this.children = children;
    }
}

class TrieNodeServiceAlt {
    void insertIntoTrie(TrieNodeAlt node, String str) {
        if (str == null || str.length() == 0) return;
        char nextChar = str.charAt(0);
        TrieNodeAlt nextNode = node.children.get(nextChar);
        if (nextNode == null) {
            nextNode = new TrieNodeAlt(nextChar, false);
            node.children.put(nextChar, nextNode);
        }
        if (str.length() == 1) {
            nextNode.word = true;
        } else {
            insertIntoTrie(nextNode, str.substring(1));
        }
    }
    TrieNodeAlt getNewEmptyTrie () {
        return new TrieNodeAlt(null, false);
    }
}