package SportsCodes;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        LowestCommonAncestor instance = new LowestCommonAncestor();
        LowestCommonAncestor instance2 = new LowestCommonAncestor();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        System.out.println((instance.lowestCommonAncestor(root,
                root.left,
                root.right
        )).val);
        System.out.println((instance2.lowestCommonAncestor(root,
                root.left,
                root.left.right.right
        )).val);
    }


    TreeNode solution;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return solution;
    }

    public boolean[] dfs(TreeNode root, TreeNode p, TreeNode q){


        // if (root.val==5){
        //     System.out.println(found[0]);
        //     System.out.println(found[1]);
        // }


        if (root==null) return null;
        if (solution != null) return null;

        boolean[] sol = new boolean[2];

        boolean[] leftsol=null;

        sol[0] = root == p;
        sol[1] = root == q;

        if (root.left != null){
            leftsol = dfs(root.left, p, q);
            sol[0] = sol[0] || (leftsol != null && leftsol[0]);
            sol[1] = sol[1] || (leftsol != null && leftsol[1]);
        }
        leftsol = null;
        if (root.right != null){
            leftsol = dfs(root.right, p, q);
            sol[0] = sol[0] || (leftsol != null && leftsol[0]);
            sol[1] = sol[1] || (leftsol != null && leftsol[1]);
        }

//        found[0] = found[0] || sol[0];
//        found[1] = found[1] || sol[1];

        solution = solution==null && sol[0] && sol[1] ? root : solution;

        return sol;
    }
}
