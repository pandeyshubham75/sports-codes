package SportsCodes;

public class BSTToGreaterTree {
    public static void main(String[] args) {
        BSTToGreaterTree i = new BSTToGreaterTree();
        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(6);
        t.left.left = new TreeNode(0);
        t.left.right = new TreeNode(2);
        t.right.left = new TreeNode(5);
        t.right.right = new TreeNode(7);
        t.left.right.right = new TreeNode(3);
        t.right.right.right = new TreeNode(8);
        i.convertBST(t);
        System.out.println(t.val);
    }
    public TreeNode convertBST(TreeNode root) {
        preOrderWithWrite(root, 0);
        return root;
    }

    public int newValue(TreeNode root, int n){
        int v = n + root.val;
        if(root.right != null) {
            v += newValue(root.right, 0);
        }
        if (root.left != null) newValue(root.left, v);
        root.val = v;
        return v;
    }

    public int preOrderWithWrite(TreeNode root, int n){
        if (root.right != null) {
            n = preOrderWithWrite(root.right, n);
        }
        n += root.val;
        root.val = n;
        if (root.left != null) {
            n = preOrderWithWrite(root.left, n);
        }
        return n;
    }
}
