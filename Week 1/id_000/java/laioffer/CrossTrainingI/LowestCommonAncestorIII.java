package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

public class LowestCommonAncestorIII {

    public static void main(String[] args) {
        System.out.println(new LowestCommonAncestorIII().lowestCommonAncestor(new TreeNode(1), new TreeNode(2), new TreeNode(3)));
    }

    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        // write your solution here
        TreeNode lca = getLCA(root, one, two);

        // 如果返回值是one或者two，那么有可能另外一个node不在root下
        // 对另外一个node做一次LCA来判断
       if (lca == one) {
            if (getLCA(root, two, two) != two) {
                return null;
            }
        } else if (lca == two) {
            if (getLCA(root, one, one) != one) {
                return null;
            }
        }
        return lca;
    }

    private TreeNode getLCA(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }

        TreeNode left = getLCA(root.left, one, two);
        TreeNode right = getLCA(root.right, one ,two);

        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }
}
