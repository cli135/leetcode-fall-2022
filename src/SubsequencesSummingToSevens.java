import java.io.*;
import java.util.Scanner;

public class SubsequencesSummingToSevens
{

    public static void main(String[] args) throws IOException {

        FileInputStream fileInStream = new FileInputStream("div7.in");
        Scanner scnr = new Scanner(fileInStream);
        int n = scnr.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scnr.nextInt();
        }
        // finding some prefix sums and suffix sums

        int[] prefixSums = new int[n + 1];
//        int[] suffixSums = new int[n];

        // prefix summing
        int accumulate = 0;
        for (int i = 0; i < n; i++) {
            accumulate = accumulate + arr[i];
            prefixSums[i] = accumulate;
        }

        // this is really just the O(n^3) to O(n^2) optimization
        // hopefully quadratic time is still fast enough
        // otherwise maybe there is a faster solution

        int maxSizeOfGroup = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // [i, j), inclusive exclusive
                int sumIDs = prefixSums[j] - prefixSums[i];
                if (sumIDs % 7 == 0) {
                    int sizeOfThisGroup = j - i;
                    maxSizeOfGroup = Math.max(maxSizeOfGroup, sizeOfThisGroup);
                }
            }
        }

        fileInStream.close();
        FileOutputStream fileOutStream = new FileOutputStream("div7.out");
        PrintWriter pw = new PrintWriter(fileOutStream);
        pw.println(maxSizeOfGroup);
        pw.close();
        fileOutStream.close();

    }
}
