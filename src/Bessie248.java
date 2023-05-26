import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Bessie248 {
    HashMap<List<Integer>, Integer> memo = new HashMap<>();
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(scnr.nextInt());
        }
    }
    private static int solve248(List<Integer> list) {
        int n = list.size();
        int[][] dp = new int[n][n+1];
        // [start, end)
        // base case, above diagonal
        for (int i = 0; i < n; i++) {
            dp[i][i+1] = list.get(i);
        }
        // dp[l][r] = max over all dp[l][i]

        return -1; // stub

    }
}
