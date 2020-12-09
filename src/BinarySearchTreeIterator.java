import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeIterator {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(7);
        t.left = new TreeNode(3);
        t.right = new TreeNode(15);
//        t.left.left = new TreeNode(4);
//        t.left.right = new TreeNode(5);
        t.right.left = new TreeNode(9);
        t.right.right = new TreeNode(20);
        BSTIterator bSTIterator = new BSTIterator(t);
        System.out.println(bSTIterator.next());    // return 3
        System.out.println(bSTIterator.next());    // return 7
        System.out.println(bSTIterator.hasNext()); // return True
        System.out.println(bSTIterator.next());    // return 9
        System.out.println(bSTIterator.hasNext()); // return True
        System.out.println(bSTIterator.next());    // return 15
        System.out.println(bSTIterator.hasNext()); // return True
        System.out.println(bSTIterator.next());    // return 20
        System.out.println(bSTIterator.hasNext()); // return False
    }
}

class BSTIterator {

    Stack<TreeNode> heightList;

    public BSTIterator(TreeNode root) {
        heightList = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            heightList.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode last = heightList.pop();
        int val = last.val;
        last = last.right;
        if (last != null) {
            heightList.push(last);
            while (last.left != null) {
                heightList.push(last.left);
                last = last.left;
            }
        }
        return val;
    }

    public boolean hasNext() {
        return !heightList.isEmpty();
    }
}