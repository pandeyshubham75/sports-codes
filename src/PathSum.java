import java.util.ArrayList;
import java.util.List;

public class PathSum {
//    static int total;
    public static void main(String[] args) {
        PathSum i = new PathSum();
        TreeNode t = new TreeNode(10);
        t.left = new TreeNode(5);
        t.right = new TreeNode(-3);
        t.left.left = new TreeNode(3);
        t.left.right = new TreeNode(2);
        t.right.right = new TreeNode(11);
        t.left.left.left = new TreeNode(3);
        t.left.left.right = new TreeNode(-2);
        t.left.right.right = new TreeNode(1);

//        TreeNode t = new TreeNode(1);
//        t.right = new TreeNode(2);
//        t.right.right = new TreeNode(3);
//        t.right.right.right = new TreeNode(4);
//        t.right.right.right.right = new TreeNode(5);

        System.out.println(i.pathSum(t, 8));
    }
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumAbsolute(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int pathSumAbsolute(TreeNode root, int sum) {
        if (root == null) return 0;
        int total = 0;
        int remainingValue = sum - root.val;
        if (remainingValue == 0) {
            System.out.println(sum); System.out.println(root.val); total++;}
        return total + pathSumAbsolute(root.left, remainingValue) + pathSumAbsolute(root.right, remainingValue);
    }
}
