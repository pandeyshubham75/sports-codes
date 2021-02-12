package SportsCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllMatchingWords {
    public static void main(String[] args) {
        AllMatchingWords instance = new AllMatchingWords();
        List<String> wordDict = Arrays.asList(new String[]{"apple", "pen", "applepen", "pine", "pineapple"});
        System.out.println(instance.wordBreak("pineapplepenapple", wordDict));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        TrieNodeServiceAlt trieNodeService = new TrieNodeServiceAlt();
        TrieNodeAlt dictionary = trieNodeService.getNewEmptyTrie();
        for (String word : wordDict) trieNodeService.insertIntoTrie(dictionary, word);
        List<String> matchingSentences = new ArrayList<>();
        if (s.length() < 1) return matchingSentences;
        checkMatchesRecursive("", dictionary, s, matchingSentences, dictionary);
        return matchingSentences;
    }

    public void checkMatchesRecursive(String sentenceTillNow, TrieNodeAlt trieNode, String remainingString, List<String> matchingSentences, TrieNodeAlt rootNode) {
        if (remainingString.length() == 0 && trieNode.word) {
            matchingSentences.add(sentenceTillNow);
            return;
        }
        if (trieNode.word) {
            checkMatchesRecursive(sentenceTillNow+" ", rootNode, remainingString, matchingSentences, rootNode);
        }
        if (remainingString.length() == 0) {
            return;
        }
        char currentChar = remainingString.charAt(0);
        if (trieNode.children.get(currentChar) == null) {
            return;
        }
        checkMatchesRecursive(sentenceTillNow+currentChar, trieNode.children.get(currentChar), remainingString.substring(1), matchingSentences, rootNode);
    }
}