package SportsCodes;

import java.util.LinkedList;
import java.util.Queue;

public class HouseRobber3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                4,
                new TreeNode(
                        1,
                        new TreeNode(
                                2,
                                new TreeNode(3),
                                null
                        ),
                        null
                ),
                null
        );
        System.out.println(rob(root));
    }

    public static int rob(TreeNode root) {
        if (root == null) return 0;
        TreeNode maxInclTree = new TreeNode();
        TreeNode maxExclTree = new TreeNode();
        getMaxSum(root, maxInclTree, maxExclTree);
        return Math.max(maxExclTree.val, maxInclTree.val);
//        Queue<TreeNode> rootQueue = new LinkedList<>();
//        rootQueue.add(root);
//        int[] maxes = lastTwoRowsMax(rootQueue);
//        return Math.max(maxes[0], maxes[1]);
    }

    public static void getMaxSum(TreeNode root, TreeNode maxInclTree, TreeNode maxExclTree) {
        if (root == null) return;
        maxInclTree.val = root.val;
        maxExclTree.val = 0;
        if (root.left != null) {
            maxInclTree.left = new TreeNode();
            maxExclTree.left = new TreeNode();
            getMaxSum(root.left, maxInclTree.left, maxExclTree.left);
            maxExclTree.val += Math.max(maxInclTree.left.val, maxExclTree.left.val);
            maxInclTree.val += maxExclTree.left.val;
        }
        if (root.right != null) {
            maxInclTree.right = new TreeNode();
            maxExclTree.right = new TreeNode();
            getMaxSum(root.right, maxInclTree.right, maxExclTree.right);
            maxExclTree.val += Math.max(maxInclTree.right.val, maxExclTree.right.val);
            maxInclTree.val += maxExclTree.right.val;
        }
    }

    public static int[] lastTwoRowsMax(Queue<TreeNode> thisLevelQueue) { // first elem of array is immediate next and second is next to next
        if (thisLevelQueue.isEmpty()) return new int[] {0,0};
        Queue<TreeNode> nextLevelQueue = new LinkedList<>();
        int thisLevelSum = 0;
        while (!thisLevelQueue.isEmpty()){
            TreeNode node = thisLevelQueue.poll();
            thisLevelSum+=node.val;
            if (node.left != null) nextLevelQueue.add(node.left);
            if (node.right != null) nextLevelQueue.add(node.right);
        }
        int[] nextTwoMaxes = lastTwoRowsMax(nextLevelQueue);
        int nextMax = Math.max(thisLevelSum+nextTwoMaxes[1], nextTwoMaxes[0]);
        nextTwoMaxes[1] = nextTwoMaxes[0];
        nextTwoMaxes[0] = nextMax;
        return nextTwoMaxes;
    }

}
