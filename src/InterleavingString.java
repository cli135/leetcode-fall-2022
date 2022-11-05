class InterleavingString {
    public static void main(String[] args) {
        /*
        "aabcc"
"dbbca"
"aadbbcbcac"
         */
        InterleavingString is = new InterleavingString();
        System.out.println(is.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
    static String s1;
    static String s2;
    static String s3;
    public boolean isInterleave(String s1, String s2, String s3) {
        InterleavingString.s1 = s1;
        InterleavingString.s2 = s2;
        InterleavingString.s3 = s3;
        return isInterleave(0, 0);
    }
    // read only globals

    // k == i + j
    // ok now this is Time Limit Exceeded, 93/106 test cases passed
    // last test case failed:
    /*
    s1 = "cbcccbabbccbbcccbbbcabbbabcababbbbbbaccaccbabbaacbaabbbc"
    s2 = "abcbbcaababccacbaaaccbabaabbaaabcbababbcccbbabbbcbbb"
    s3 = "abcbcccbacbbbbccbcbcacacbbbbacabbbabbcacbcaabcbaaacbcbbbabbbaacacbbaaaabccbcbaabbbaaabbcccbcbabababbbcbbbcbb"

    so yeah i guess now it looks like time to do memo and
    ground up iterative dp
    */
    private boolean isInterleave(int i, int j) {
        // have to have three checks:
        // 1. you made it successfully to the end of s3
        //      without being turned to false midway/rejected somewhere
        //      along the way
        // 2. you made it successfully to the end of the s1
        //      proving you used all in s1
        // 3. you made it successfully to the end of the s2
        //      proving you used all in s2
        //
        // the last two checks are seen in this example:
        // s1 = "a", s2 = "b", s3 = "a"
        // originally was incorrectly returning true
        // when it really should return false
        // because while s3 is at the end of its string,
        // and s1 is too,
        // you know that you didn't use all the chars in j/s2
        // which is part of the requirements of the problem
        // to interleave and use all characters and not leave
        // any character out or omit anything (i.e. have to use
        // all and can't simply pick and choose)
        if (i + j >= s3.length() && i >= s1.length() && j >= s2.length()) {
            return true;
        }
        // xor
        // something about "" and substring length
        // else if (i > s1.length() || j > s2.length()) {
        //     return false; // dead end here, ran out
        // }
        // this ended up becoming the short circuit evaluation below
        // since we want a dynamic, at-runtime check to see
        // whether we should enter each recursive call, tailored
        // to the index out of bounds requirements for that particular
        // recursive call, instead of straight up rejecting anything
        // that doesn't pass at the beginning

        // -------------------------------------------
        // you need to code the true and false base case
        // when you are doing boolean branching recursion
        // example:
        // isInterleave("aabcc", "dbbca", "aadbbcbcac");
        // should return true, but there was never a true
        // leaf or base case for it to use as a return value
        // -------------------------------------------
        // the above is very important -- if you want it
        // to return true, you have to hardcode it in a base
        // case somewhere
        // make sure your base case is right and can return
        // multiple values or that you have multiple base
        // cases if they are necessary
        // -------------------------------------------



        // recursive case
        boolean b1 = false; // init
        // short circuit evaluation is key here
        // this is the dynamic, at-runtime indexing checks
        // to make sure we do not cause idx out of bounds in s1
        // and also to avoid idx out of bounds in s3
        // in case s3 is actually just shorter than s1 + s2 or something
        // like that, i am not quite sure of all of the reasons why
        // we need these short circuit eval checks
        // besides the fact that it keeps our indices good
        // and within acceptable ranges
        // and ending the infinite stack of recursion
        // before we hit an indexoutofbounds error

        // check if we can advance one in s1 with i
        // indexing checks
        if (i < s1.length() && (i + j) < s3.length() && s1.charAt(i) == s3.charAt(i + j)) {
            // advance one in i and k
            b1 = isInterleave(i + 1, j);
        }

        // check if we can advance one in s2 with j
        // indexing checks
        boolean b2 = false; // init
        if (j < s2.length() && (i + j) < s3.length() && s2.charAt(j) == s3.charAt(i + j)) {
            // advance one in j and k
            b2 = isInterleave(i, j + 1);
        }

        // as long as one turned out okay, we are good
        // we just need at least one path
        return b1 || b2;
    }
}