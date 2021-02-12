package SportsCodes;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubtreeWithDeepestNodes {
    public static void main(String[] args) {
        SmallestSubtreeWithDeepestNodes i = new SmallestSubtreeWithDeepestNodes();
        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(5);
        t.right = new TreeNode(1);
        t.left.left = new TreeNode(6);
        t.left.right = new TreeNode(2);
        t.right.left = new TreeNode(0);
        t.right.right = new TreeNode(8);
        t.left.right.left = new TreeNode(7);
        t.left.right.right = new TreeNode(4);

        t = i.subtreeWithAllDeepest(t);
        System.out.println(t.val);

//        t = new TreeNode(0);
//        t.left = new TreeNode(1);
//        t.right = new TreeNode(3);
//        t.left.right = new TreeNode(2);
//
//        t = i.subtreeWithAllDeepest(t);
//        System.out.println(t.val);
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> heights = new HashMap<>();
        memoizeHeights(heights, root);
        return subtreeWithAllDeepest(root, heights);

        // Commented code below this, is the method to do it without memoizing height

//        if (root.left == null && root.right == null) return root;
//        int lHeight = getHeight(root.left), rHeight = getHeight(root.right);
//        if (lHeight == rHeight) return root;
//        if (lHeight > rHeight) return subtreeWithAllDeepest(root.left);
//        return subtreeWithAllDeepest(root.right);
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root, Map<TreeNode, Integer> heights) {
        if (root.left == null && root.right == null) return root;
        int lHeight = root.left == null ? 0 : heights.get(root.left);
        int rHeight = root.right == null ? 0 : heights.get(root.right);
        if (lHeight == rHeight) return root;
        if (lHeight > rHeight) return subtreeWithAllDeepest(root.left, heights);
        return subtreeWithAllDeepest(root.right, heights);
    }

    static int memoizeHeights(Map<TreeNode, Integer> heights, TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = Math.max(memoizeHeights(heights, root.left), memoizeHeights(heights, root.right)) + 1;
        heights.put(root, height);
        return height;
    }

    static int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
