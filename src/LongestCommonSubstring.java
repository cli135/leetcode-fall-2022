import java.util.HashMap;

class LongestCommonSubstring {
    public static void main(String[] args) {
        LongestCommonSubstring l = new LongestCommonSubstring();

        int x = l.longestCommonSubsequence("bsb", "kbk");
        int y = l.longestCommonSubsequence("abcdeee", "acebdcd");
        System.out.println(x);
        System.out.println(y);
    }

    public int longestCommonSubsequence(String text1, String text2) {

        // +1 for last index, the empty string
        // dp grid has result of all substring comparisons in one place
        // built from ground up
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];


        // filling in base cases with zeroes (along the edge of pascal's triangle)
        for (int j = 0; j < text2.length() + 1; j++) {
            dp[text1.length()][j] = 0;
        }
        for (int i = 0; i < text1.length() + 1; i++) {
            dp[i][text2.length()] = 0;
        }

        // starting from bottom right and going up, then left

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // eat this char and +1 moving along in both
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
                else {
                    // none to be gotten at this index
                    // move only one char, straddling
                    // tadashi tokeida method
                    // both == l + r == r + l so no need to code for both
                    dp[i][j] = 0 + Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    // this is dynamic programming
    // start with a nondeterministic finite automaton

    // nondeterministic finite automata for the win!!!
    // np hard problem in the general case

    // 9/45 test cases passed, but i am much more sure that it is
    // doing well, since it got a Time Limit Exceeded

    // cool

    // i really really really miss binary tree questions, not even going to lie, i love binary trees, i need to do more problems

    // also graphs are okay sometimes
    // memo cache

    // deterministic when given two strings

    // notes:
    // cache *everything* in memo, right after you compute it
    // even like things like max for 0,0
    // also don't forget x,y is y,x symmetrically too in the cache

    HashMap<String, Integer> cache = new HashMap<>();
    public int longestCommonSubsequence2(String text1, String text2) {

        if (cache.containsKey(text1 + "," + text2)) {
            return cache.get(text1 + "," + text2);
            // go memo!
        }
        else if (cache.containsKey(text2 + "," + text1)) {
            return cache.get(text2 + "," + text1);
            // go memo!
        }
        int m = text1.length();
        int n  = text2.length();

        if (m == 0 || n == 0) {
            return 0;
        }
        else if (text1.charAt(0) == text2.charAt(0)) {
            int x = 1 + longestCommonSubsequence(text1.substring(1), text2.substring(1));

            // the below line is wrong because it's attributing the parent
            // function call's work to the child funciton
            // where the parent function is a superset of the child function

            // cache.put(text1.substring(1) + "," + text2.substring(1), x);

            // make sure your indices are right when storing memo cache
            // sb,k was getting 1 because it was getting the 1 from sb,bk
            // which doesn't make any sense, since sb,k should be 0 in the cache
            // the child shouldn't get the credit for something the parent has done, speaking in terms of computer science nodes strictly

            // that violates the one-way princple of dynamic programming
            // solutions are constructed from bottom up, one way/direction

            // always be sure to not have advanced an index or both indices,
            // especially when caching

            // always be sure that the work is attributed to the correct
            // function level, not the sub function, attributed to the correct
            // level

            // so we don't get weird scnearios like sb,k -> 1
            cache.put(text1.substring(0) + "," + text2.substring(0), x);
            return x;
        }
        else {
            // advance i , j , or both
            // and choosing the best -- nfa style :)

            int i = longestCommonSubsequence(text1.substring(1), text2.substring(0));
            cache.put(text1.substring(1) + "," + text2.substring(0), i);

            int j = longestCommonSubsequence(text1.substring(0), text2.substring(1));
            cache.put(text1.substring(0) + "," + text2.substring(1), j);

            // int both = longestCommonSubsequence(text1.substring(1), text2.substring(1));
            // cache.put(text1.substring(1) + "," + text2.substring(1), both);

            // int max = Math.max(i, Math.max(j, both));
            int max = Math.max(i, j);

            cache.put(text1.substring(0) + "," + text2.substring(0), max);
            return max;
        }

    }

    /*

        int i, j = 0;
        int count = 0;
        while (i < text1.length() && j < text2.length()) {

            if (text1.charAt(i) == text2.charAt(j)) {
                i++;
                j++;
                count++;
            }
            else {
                // move i only
                // move j only
                // move i and j
                int moveIOnly = longestCommonSubsequence(text1.substring(i + 1), text2.substring(j));
                int moveJOnly = longestCommonSubsequence(text1.substring(i), text2.substring(j + 1));
                int moveIAndJ = longestCommonSubsequence(text1.substring(i + 1), text2.substring(j + 1));
                return

            }

        }
    */
}