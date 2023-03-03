/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null; // i guess?
        }
        // at this point, root non null

        if (root.val == p.val) {
            return p;
        }
        else if (root.val == q.val) {
            return q;
        }
        else {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if ((left == p && right == q) || (right == p && left == q)) {
                return root;
            }
            else if (left == null) {
                // search right
                return right;
            }
            else {
                return left;
            }
        }
    }
}