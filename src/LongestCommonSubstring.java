import java.util.HashMap;

class LongestCommonSubstring {
    public static void main(String[] args) {
        LongestCommonSubstring l = new LongestCommonSubstring();

        int x = l.longestCommonSubsequence("bsb", "kbk");
        System.out.println(x);
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
    public int longestCommonSubsequence(String text1, String text2) {

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
            // which doesn't make any sense

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

//        public static int longestCommonSubsequence1(String text1, String text2) {
//        int m = text1.length();
//        int n = text2.length();
//
//        if (m == 0 || n == 0) {
//            return 0;
//        }
//        else if (m == 1 && n == 1) {
//            if (text1.equals(text2)) {
//                return 1;
//            }
//            else {
//                return 0;
//            }
//        }
//        else if (m == 1) {
//            for (int i = 0; i < n; i++) {
//                if (text1.charAt(0) == text2.charAt(i)) {
//                    return 1;
//                }
//            }
//            return 0;
//        }
//        else if (n == 1) {
//            for (int i = 0; i < m; i++) {
//                if (text2.charAt(0) == text1.charAt(i)) {
//                    return 1;
//                }
//            }
//            return 0;
//        }
//
//        String left1 = text1.substring(0, m / 2);
//        String left2 = text2.substring(0, n / 2);
//        String right1 = text1.substring(m / 2, m);
//        String right2 = text2.substring(n / 2, n);
//
//        return longestCommonSubsequence1(left1, left2) + longestCommonSubsequence1(right1, right2);
//
//    }
}