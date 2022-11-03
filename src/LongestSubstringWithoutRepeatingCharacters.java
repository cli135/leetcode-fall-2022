public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(l.lengthOfLongestSubstring("pwwkew"));
    }
    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int maxLen = 0;
        int[] cache = new int[256];
        while (l < s.length() && r < s.length()) {
            // char c = s.charAt(l);
            char d = s.charAt(r);
            // cache[c]++;
            cache[d]++;
            if (!containsDuplicate(cache)) {
                // fix later
                maxLen = Math.max(r - l + 1, maxLen);
                r++;

            }
            else {
                char c = s.charAt(l);
                cache[c]--;
                l++;


                // l = r;
                // cache = new int[256];
            }
        }
        return maxLen;
    }
    private boolean containsDuplicate(int[] cache) {
        for (int amt : cache) {
            if (amt >= 2) {
                return true;
            }
        }
        return false;
    }

}
