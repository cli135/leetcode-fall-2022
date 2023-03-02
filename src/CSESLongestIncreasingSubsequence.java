import java.util.Scanner;

public class CSESLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // reading in input
        int n = scnr.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scnr.nextInt();
        }
//        System.out.println(lengthOfLongestIncreasingSubsequence0(arr));
//        System.out.println(lengthOfLongestIncreasingSubsequence1(arr));
        System.out.println(lengthOfLongestIncreasingSubsequence2(arr));

//
    }

    public static int lengthOfLongestIncreasingSubsequence0(int[] arr) {


        return 0; // method stub
    }
    // indices are inclusive
//    public static int lengthOfLongestIncreasingSubsequence0(int[] arr, int i, int j) {
//        int max =
//        for (int k = i; k < j; k++) {
//
//        }
//
//        return 0; // method stub
//    }

    public static int lengthOfLongestIncreasingSubsequence2(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            // let dp[i] be the length of the
            // longest increasing subsequence *ending*
            // at index i, (I think inclusive)
            // definition from USACO guide
            // i think this might mean you go forward in the array
            // to accumulate at the back end i guess
//            dp[i] = 1; // default value if loop doesn't run
            dp[i] = 0; // to begin with
            // meaning we only keep the one at the index i in arr
            for (int k = 0; k < i; k++) {
                // i switched k and i but it should be fine
                if (arr[i] > arr[k]) {
                    // finding the max of all previous??
                    dp[i] = Math.max(dp[i], dp[k]);
                }
            }
            dp[i] += 1; // as a default value and add on in all cases

        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
//        return 0; // method stub
    }
    public static int lengthOfLongestIncreasingSubsequence1(int[] arr) {
        // dp array
        // where start and end are the indices
        int n = arr.length;
        int[][] dp = new int[n][n];
        // base case where start == end
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
//        dp[n][n] = 1;
//        for (int j = n - 1; j >= 0; j--) {
//            for (int i = j; i >= 0; i--) {
//
//            }
//        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // linear scan
                for (int k = i; k <= j; k++) {
                    if (arr[j] > arr[k]) {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i][k]);
                    }
                    else {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k]);
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i][n-1]);
        }
        int test = 5;
        return max;
//        return dp[0][n - 1];
    }
}
