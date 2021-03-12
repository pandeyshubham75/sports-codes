package SportsCodes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HasAllCOdes {

    public static void main(String[] args) {
        HasAllCOdes i = new HasAllCOdes();
        System.out.println(i.hasAllCodes("00110110", 2));
        System.out.println(i.hasAllCodes("00110", 2));
        System.out.println(i.hasAllCodes("0110", 1));
        System.out.println(i.hasAllCodes("0110", 2));
        System.out.println(i.hasAllCodes("0000000001011100", 4));
    }


    public boolean hasAllCodesLame(String s, int k) {
        if (s == null || s.length() < k) return false;
        if (k == 0) return true;
        int unq = 0;
        HasAllCodesNode root = new HasAllCodesNode();
        char[] chars = new char[k];
        int req = (int) Math.pow(2, k);
        Set<String> h = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++){
            s.getChars(i, i+k, chars, 0);
            unq = addToTree(root,
                    chars,
                    0,
                    unq);
            if (unq == req) return true;
        }
        return false;
    }

    public boolean hasAllCodes(String s, int k) {
        if (s == null || s.length() < k) return false;
        if (k == 0) return true;
        int unq = 0;
        int req = (int) Math.pow(2, k);
        Set<String> h = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++){
            h.add(s.substring(i, i+k));
            if (h.size() == req) return true;
        }
        return h.size() == req;
    }

    int addToTree(HasAllCodesNode node, char[] c, int start, int unq){
        if (start == c.length) return unq;
        if (c[start] == '0'){
            if (node.left == null){
                node.left = new HasAllCodesNode();
                if (start == c.length-1) unq++;
            }
            node = node.left;
        } else {
            if (node.right == null){
                node.right = new HasAllCodesNode();
                if (start == c.length-1) unq++;
            }
            node = node.right;
        }
        return start < c.length ? addToTree(node, c, start+1, unq) : unq;
    }
}

class HasAllCodesNode {
    HasAllCodesNode left;
    HasAllCodesNode right;
}