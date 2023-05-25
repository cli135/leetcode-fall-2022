/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null; // i guess?
        }
        // at this point, root non null

        if (root.val == p.val) {
            return p; // found p
            // at lower levels we need to do some checks
            // since this is just find information, i.e. found it
            // but q could be in the other branch
        }
        else if (root.val == q.val) {
            return q; // found q
            // at root this works for lca
            // at lower levels we need to do some checks
            // since this is just find information, i.e. found it
            // but p could be in the other branch
        }
        else {
            // these should really be find() calls as a helper function
            // to improve clarity and show how it's actually working
            // consider three or four cases:
            // 1. p, q split, that is, p in left and q in right or q in left and p in right
            // 2. p, q both in left
            // 3. p, q both in right
            // we claim and can try to show that in all cases (1,2,3),
            // the algorithm returns the correct result, the lowest common ancestor

            // these calls really should be calls to a find() helper function i think
            // cause a lot of the logic is mixed and happens to work out alright
            // but it could be much clearer
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if ((left == p && right == q) || (right == p && left == q)) {
                // this is the split case which is the end of the recursion
                // this is based on the find() calls above
                return root;
            }
            // the below could work but the way i currently have it written
            // it is more like a find function not a really an lca function
            // the below is actually for an lca function
            // else
            // if (left == null && right == null) {
            //     return root;
            // }
            else if (left == null) {
                // search right
                // since you definitely know p, q both in right
                // this is a reduction of the problem, continuing recursion
                return right;
            }
            else {
                // symmetrical case, you know p, q both in left
                // this is a reduction of the problem, continuing recursion
                return left;
            }
        }
    }
}