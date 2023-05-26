//https://cses.fi/problemset/task/1651
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node() {
        val = 0;
        left = null;
        right = null;
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }
}
public class RangeUpdateQueries {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();
        int q = scnr.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(scnr.nextInt());
        }
        Node root = build(list, 0, list.size());
        for (int i = 0; i < q; i++) {
            int queryType = scnr.nextInt();
            if (queryType == 1) {
                // 1 a b u
                // update [a,b] range
            }
            else {
                // 2 k
            }
        }
    }
    // [i, j)
    public static Node build(List<Integer> list, int i, int j) {
        Node cur = new Node();
        cur.val = 0;
        if (i + 1>= j) {
            // leaf node time
            cur.val = list.get(i);
        }
        else {
            // intermediate node recursion time
            cur.left = build(list, i, (i + j) / 2);
            cur.right = build(list, (i + j) / 2, j);
        }
        return cur;
    }
}
