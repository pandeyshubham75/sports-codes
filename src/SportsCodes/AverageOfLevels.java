package SportsCodes;

import java.util.*;

public class AverageOfLevels {
    public static void main(String[] args) {
        AverageOfLevels i = new AverageOfLevels();
//        TreeNode t = new TreeNode(10);
//        t.left = new TreeNode(5);
//        t.right = new TreeNode(-3);
//        t.left.left = new TreeNode(3);
//        t.left.right = new TreeNode(2);
//        t.right.right = new TreeNode(11);
//        t.left.left.left = new TreeNode(3);
//        t.left.left.right = new TreeNode(-2);
//        t.left.right.right = new TreeNode(1);

        TreeNode t = new TreeNode(3);
        t.right = new TreeNode(20);
        t.right.right = new TreeNode(7);
        t.right.left = new TreeNode(15);
        t.left = new TreeNode(9);

        System.out.println(i.averageOfLevels(t));
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> sol = new ArrayList<>();
        if (root == null) return sol;

        // Approach 1
//        Queue<TreeNode> currQ = new LinkedList<>();
//        Queue<TreeNode> nextQ = new LinkedList<>();
//        Queue<TreeNode> tempQ;
//        currQ.add(root);
//        double sum = 0;
//        int count = 0;
//        while (!currQ.isEmpty() || !nextQ.isEmpty()) {
//            if (currQ.isEmpty()){
//                sol.add(sum/count);
//                sum = 0;
//                count = 0;
//                tempQ = nextQ;
//                nextQ = currQ;
//                currQ = tempQ;
//                continue;
//            }
//            TreeNode nextNode =currQ.poll();
//            sum+=nextNode.val;
//            count++;
//            if (nextNode.left != null) nextQ.add(nextNode.left);
//            if (nextNode.right != null) nextQ.add(nextNode.right);
//        }
//        if (count > 0) sol.add(sum/count);
        // 82.37% memory and 78.11% space as of 5 March 2021
// End Approach 1

        // Approach 2
        List<Integer> counts = new ArrayList<>();
        consolidateNode(root, sol, 0, counts);
        // 100% runtime and 67.25% memory
        // End Approach 2

        return sol;
    }

    public void consolidateNode(TreeNode root, List<Double> sol, int level, List<Integer> counts){
        if (root == null) return;
        if (sol.size() == level){
            sol.add((double) root.val);
            counts.add(1);
        } else {
            sol.set(level, ((counts.get(level) * sol.get(level))+root.val)/(counts.get(level)+1));
            counts.set(level, counts.get(level)+1);
        }
        if (root.left != null) consolidateNode(root.left, sol, level+1, counts);
        if (root.right != null) consolidateNode(root.right, sol, level+1, counts);
    }
}
