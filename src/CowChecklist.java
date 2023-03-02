import java.util.Scanner;

public class CowChecklist {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int h = scnr.nextInt();
        int g = scnr.nextInt();
        int[][] hCows = new int[h][2];
        int[][] gCows = new int[g][2];
        for (int i = 0; i < h; i++) {
            hCows[i][0] = scnr.nextInt();
            hCows[i][1] = scnr.nextInt();
        }
        for (int i = 0; i < g; i++) {
            gCows[i][0] = scnr.nextInt();
            gCows[i][1] = scnr.nextInt();
        }
        System.out.println(calculateMinimumEnergyVisitAllCows(hCows, gCows));
    }
    // energy is defined as sum of the squares of the individual distances
    // that farmer john travels
    public static double calculateMinimumEnergyVisitAllCows(int[][] hCows, int[][] gCows) {
        // find a way to interleave
        // since you need to calculate manhattan distance

        // kind of like merge sorted arrays logic here
        // just pick which one is closer of the two that you can visit next
        // since the ordering is kind of strict and you only
        // have two options at each step

        int hIndex = 1;
        int gIndex = 0;
        int h = hCows.length;
        int g = gCows.length;
        double[][] dp = new double[h][g];

        // go until you hit guernseys end of list
        //while (gIndex < gCows.length) {
            // compare whether the next holstein or guernsey is closer
            // wait but we need a dp grid this is not greedy

        //}

        // this is a paths on grids question
        // start from bottom right and go up to do the dp bottom up

        // the beginning and end are fixed,
        // you have to have holsteins as the first and last

        // where do i start the indices
        for (int j = g - 1; j >= 0; j--) {
            for (int i = h - 2; i >= 1; i--) {
                // dp state transition
                // bounds checks
                if (i + 1 >= h - 1 && j + 1 >= g) {
                    // just walk the last distance to the holstein cow
                    dp[i][j] = energy(hCows[i], hCows[i + 1]);
                }
                else if (i + 1 >= h - 1) {
                    // only choice is guernsey (valid index)
                    // energy value propagates / is carried up
                    dp[i][j] = dp[i][j + 1] + energy(gCows[j], gCows[j + 1]);
                }
                else if (j + 1 >= g) {
                    // only choice is holstein (valid index)
                    // energy value goes up
                    dp[i][j] = dp[i + 1][j] + energy(hCows[i], hCows[i + 1]);
                }
                else {
                    // fully fledged dynamic programming case
                    // optimization function below
                    dp[i][j] = Math.min(dp[i + 1][j] + energy(hCows[i], hCows[i + 1]),
                                        dp[i][j + 1] + energy(gCows[i], gCows[i + 1]));
                }

            }
        }

        // finish out the holsteins

        // yeah and finally there's just one holstein left
        dp[0][0] = 0;
        if (h > 1) {
            dp[0][0] = dp[1][0] + energy(hCows[0], hCows[1]);
        }

        return dp[0][0]; // method stub
    }

//    pythagorean distance formula??
    public static double distance(int[] point1, int[] point2) {
        return Math.sqrt(Math.pow((point1[0] - point2[0]), 2)
                + Math.pow((point1[1] - point2[1]), 2));
    }

    public static double energy(int[] point1, int[] point2) {
        return Math.pow(distance(point1, point2), 2);
    }
}
