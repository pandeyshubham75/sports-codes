package SportsCodes;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        VerticalOrderTraversal i = new VerticalOrderTraversal();
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.left = new TreeNode(4);
        t.left.right = new TreeNode(5);
        t.right.left = new TreeNode(6);
        t.right.right = new TreeNode(7);
        List<List<Integer>> l = i.verticalTraversal(t);
        for (List<Integer> inner : l) {
            System.out.println();
            for (int in : inner) System.out.print(in + "    ");
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map <Integer, List<RowValuePair>> values = new HashMap<>();
        int[] range = new int[]{0,0};
        traverseTreeRecursive(root, 0, 0, values, range);
        List<List<Integer>> solution = new ArrayList<>();
        for (int col = range[0]; col <= range[1]; col++) {
            List<RowValuePair> colValues = values.get(col);
            if (colValues != null) {
                colValues.sort((o1, o2) -> o2.row - o1.row != 0 ? o2.row - o1.row : o1.value - o2.value);
                solution.add(colValues.stream().map(r -> r.value).collect(Collectors.toList()));
            }
        }
        return solution;
    }

    public void traverseTreeRecursive (TreeNode root, int row, int col, Map <Integer, List<RowValuePair>> values, int[] range) {
        if (root == null) return;
        if (col < range[0]) range[0] = col;
        if (col > range[1]) range[1] = col;
        List<RowValuePair> colValues = values.get(col);
        if (colValues == null){
            colValues = new ArrayList<>();
            colValues.add(new RowValuePair(row, root.val));
            values.put(col, colValues);
        } else {
            colValues.add(new RowValuePair(row, root.val));
        }
        if (root.left != null) traverseTreeRecursive(root.left, row - 1, col - 1, values, range);
        if (root.right != null) traverseTreeRecursive(root.right, row - 1, col + 1, values, range);
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

 class RowValuePair {
    int row;
    int value;
    public RowValuePair(int row, int value) {
        this.row = row;
        this.value = value;
    }
 }