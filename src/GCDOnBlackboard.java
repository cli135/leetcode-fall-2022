import java.util.Scanner;
//public class Main {

//    submission accepted on atcoder.jp
public class GCDOnBlackboard {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scnr.nextInt();
        }
        // prefix sums
        // but with gcd
        int[] prefixGCDs = new int[n];
        int[] suffixGCDs = new int[n];

        prefixGCDs[0] = arr[0];
        for (int i = 1; i < n; i++) {
            // n-wise gcd computed with fold right
            prefixGCDs[i] = gcd(arr[i], prefixGCDs[i - 1]);
        }

        // same for suffix gcds
        suffixGCDs[n - 1] = arr[n - 1];
        // oops gotta make sure its --
        for (int i = n - 2; i >= 0; i--) {
            // n-wise gcd computed with fold left
            suffixGCDs[i] = gcd(suffixGCDs[i + 1], arr[i]);
        }

        // ok now we have both, time to uh
        // calculate the fall in on each
        // to see what the gcd of all the other numbers
        // are

        int[] fallInArray = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                // only one side
                fallInArray[i] = suffixGCDs[i + 1];
            }
            else if (i == n - 1) {
                // only one side
                fallInArray[i] = prefixGCDs[i - 1];
            }
            else {
                // two way
                fallInArray[i] = gcd(prefixGCDs[i - 1], suffixGCDs[i + 1]);
            }
        }

        // then just return the max of that array right?
        // those are the possible gcds with one removed

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, fallInArray[i]);
        }

        // finished
        System.out.println(max);

    }

    // precondition: a > b > 0
    // will flip but both need to be positive in the first place
    public static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            // base case
            return a;
        }
        // recursion, Euclidean Algorithm
        return gcd(b, a % b);
    }
}
