import java.util.Arrays;
import java.util.Scanner;

public class HaybaleStacking {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();

        int k = scnr.nextInt();

        // too direct a method,
        // we will store difference array
        // (discrete derivatives)
        // and recover the original array
        // with a prefix sum (discrete
        // integration, or a riemann sum)

//        int[] haybaleStacks = new int[n];
        // why n + 1 here? why not just n?
        // not sure
        int[] differenceArray = new int[n + 1];

        for (int i = 0 ; i < k; i++) {
            // reading and making it zero indexed
            // for sorting later
            int start = scnr.nextInt() - 1;
            int end = scnr.nextInt() - 1;

            // oh i guess end + 1 is why we have n + 1 length? yeah
            differenceArray[start] += 1; // up the mountain, when we accumulate
            differenceArray[end + 1] -= 1; // back down the slope when it's over

//            for (int j = start; j <= end; j++) {
//                haybaleStacks[j] += 1;
//            }
            // this almost makes it quadratic
            // basically dependent on the data itself
            // which is not good
            // so we can hack it with difference
            // array, which is a compressed form of a prefix
            // sum, which is like uncompressing it

            // *******************************
            // if difference array is differentiation,
            // prefix sum array is integration
            // *******************************
        }

        // i'll just do n + 1 for now and see what happens
        int[] prefixSum = new int[n + 1];
        int accumulate = 0;
        // fundamental thm of calculus,
        // integration of a derivative is just original function
        // accumulation of a discrete derivative is just original array values
        for (int i = 0; i < prefixSum.length - 1; i++) {
            accumulate = accumulate + differenceArray[i];
            prefixSum[i] = accumulate;
        }

        // it's not entirely clear that successive queries
        // will not overwrite each other
        // but i guess it's fine

        Arrays.sort(prefixSum);
        System.out.println(prefixSum[(n + 1) / 2]);
    }
}
