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
    private boolean isInterleave(int i, int j) {
        if (i >= s1.length() || j >= s2.length() || i + j >= s3.length()) {
            return false;
        }
        // recursive case
        boolean b1 = false; // init
        if (s1.charAt(i) == s3.charAt(i + j)) {
            // advance one in i and k
            b1 = isInterleave(i + 1, j);
        }

        boolean b2 = false; // init
        if (s2.charAt(j) == s3.charAt(i + j)) {
            // advance one in j and k
            b2 = isInterleave(i, j + 1);
        }


        return b1 || b2;
    }
}