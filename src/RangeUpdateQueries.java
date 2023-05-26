//https://cses.fi/problemset/task/1651
//CSES whitespace formatting was causing my program to hang - lesson learned
// just do leetcode
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
class Node {
    public int val;
    public int size;
    public Node left;
    public Node right;
    int start; // inclusive both ends
    int end; // inclusive both ends
    int leftMid; // mid according to [start, end] inclusive both ends arithmetic
    public Node(int i, int j) {
        val = 0;
        start = i;
        end = j;
        leftMid = ((i + j) / 2);
        left = null;
        right = null;
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }
}

// segment tree is really just binary search tree
// and arrays combined with fold right and fold left
// summating recursion and generalizable functor recursion
// also binary search tree is just binary search on array
// log n access mixed with O(1) insertion deletion with linked list links
public class RangeUpdateQueries {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();
        int q = scnr.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(scnr.nextInt());
        }
        Node root = build(list, 0, list.size() - 1);
        for (int i = 0; i < q; i++) {
            int queryType = scnr.nextInt();
            if (queryType == 1) {
                // 1 a b u
                // update [a,b] range
                // they are doing 1 indexing
                // i am doing 0 indexing, so adjust by 1 to fix
                int a = scnr.nextInt() - 1;
                int b = scnr.nextInt() - 1;
                int u = scnr.nextInt();
                update(root, a, b, u);
            }
            else if (queryType == 2) {
                // 2 k
                int k = scnr.nextInt() - 1;
                int result = get(root, k);
                System.out.println(result);
            }
        }
    }
    // [i, j]
    public static Node build(List<Integer> list, int i, int j) {
        Node cur = new Node(i,j);
        cur.val = 0;
        if (i >= j) {
            // leaf node time
            cur.val = list.get(i);
        }
        else {
            // intermediate node recursion time
            cur.left = build(list, i, (i + j) / 2);
            cur.right = build(list, ((i + j) / 2) + 1, j);
        }
        return cur;
    }
    public static void update(Node node, int i, int j, int u) {
        // somehow split it up into sums of intermediate nodes
        // where there are no more than two nodes per level
        // and the level with two nodes is like the last level right?
        // yeah, from what I see yeah, seems to work like that
        if (node == null) {
            return; // right? not sure
        }
        // base case non degenerate when we have a nice power of two update query
        if (i == node.start && j == node.end) {
            node.val += u;
            return;
        }
        // recursive cases below
        // all left
        if (i <= j && j <= node.leftMid) {
            // recurse left
            update(node.left, i, j, u);
        }
        // all right
        else if (node.leftMid + 1 <= i && i <= j) {
            // recurse right
            update(node.right, i, j, u);
        }
        else if (i <= node.leftMid && node.leftMid + 1 <= j) {
            // going across the middle gap
            // split up into two recursions, left and right
            // keeping the outermost bounds
            // and do both recursions
            // and make sure the results of both are updated
            // and reflected
            update(node.left, i, node.leftMid, u);
            update(node.right, node.leftMid + 1, j, u);
        }

    }
    // let's fold right for semantic use and then we can
    // optimize the fold left later, so no tail recursion yet
    public static int get(Node node, int i) {
        if (node == null) {
            return 0; // base case degenerate
            // but actually used in this recursion
            // as the signal for beyond-leaf
        }
        // do a log n recursion from root to leaf
        // recursive case
        if (i <= node.leftMid) {
            // going left in the recursion
            return node.val + get(node.left, i);
        }
        else { // node.leftMid + 1 <= i
            // going right in the recursion
            return node.val + get(node.right, i);
        }
    }
}
