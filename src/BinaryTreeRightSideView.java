import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
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
//  i wonder if you can write this in ocaml
// that would be a cool exercise
// what if you did all practiceit and leetcode problems
// but in ocaml. that would be crazy haha
// but it would be a good ocaml exercise in trees
class BinaryTreeRightSideView {
    // my attempt at doing postorder with
    // overwriting an accumulated array
    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
    public List<Integer> rightSideView(TreeNode root) {
        // I think it's only possible to do O(N)
        // because if you tried to do log(N) you would
        // need to know ahead of time which branches to go down
        // and you'd need to go down those branches anyways
        // so O(N) probably is the best we can do
        // first find depth in O(log(N)) time
        int height = depth(root);
        ArrayList<Integer> accumulated = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            accumulated.add(-101); // 1 below the min val for nodes
            // error flag, which will change later
        }
        return rightSideViewHelper(root, accumulated, 0);
    }
    // this is a fold left style function, accumulating on the way down
    public List<Integer> rightSideViewHelper(TreeNode root, List<Integer> accumulated, int depth) {
        if (root == null) {
            return accumulated; // fold left style
            // returning what we've accumulated so far
        }
        // depth valid here now

        // now we go postorder L, R, cur traversal
        // i think if we switch these two lines we'll get the
        // binary tree left side view, since it's like mirror image
        // postorder in that case, going up the left side of the tree
        // at the end
        accumulated = rightSideViewHelper(root.left, accumulated, depth + 1);
        accumulated = rightSideViewHelper(root.right, accumulated, depth + 1);
        // cur now, on the depth index, we set it to the val
        // in ocaml functional programming we wouldn't be able to do
        // this. i wonder if some algorithms are possible to compute in ocaml
        // but really inefficient due to the cons (::) handicap that ocaml
        // seems to have. so like it's still turing complete but just really
        // slow because it's functional with no shortcuts
        // i like functional but i also like imperative shortcuts like the line
        // below
        accumulated.set(depth, root.val);

        return accumulated; // this should be good
        // since we go in postorder
    }
    // works for test cases only on right branch
    // but left branch could extend lower, so need a new solution
    public List<Integer> rightSideView1(TreeNode root) {
        if (root == null) {
            // or maybe i should return null idk
            return new ArrayList<>();
        }
        // when you can go right, go right
        // otherwise, go left if you are forced to
        // but always go right if you can because
        // that is what is visible from the right side
        // but first add this root to the list
        ArrayList<Integer> list = new ArrayList<>();
        list.add(root.val);
        if (root.right != null) {
            // https://stackoverflow.com/questions/54675873/error-incompatible-types-boolean-cannot-be-converted-to-liststring
            // I need to first add all and then return when i'm done
            // instead of trying to do it all in one step
            // void method below
            list.addAll(rightSideView(root.right));
            return list;
        }
        else {
            list.addAll(rightSideView(root.left));
            return list;
        }
    }
}