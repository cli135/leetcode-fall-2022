import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = s.maxSlidingWindow(nums, k);
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        // base cases
        if (nums.length <= 1) {
            return nums;
        }
        else if (nums.length == 2) {
            if (k == 1) {
                return nums;
            }
            else {
                int max = Math.max(nums[0], nums[1]);
                return new int[]{max};
            }
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.MIN_VALUE; // remember that arrays
            // init to 0 and not the min value or max value
            // when using an array as a min or max value storing array
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < k; j++) {
                int lower = i - j;
                // int upper = i + j;
                if (lower >= 0) {
                    result[lower] = Math.max(result[lower], nums[i]);
                }
                // if (upper < nums.length) {
                //     result[upper] = Math.max(result[upper], nums[i]);
                // }
            }
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            res[i - k + 1] = result[i];
        }
        // for (int i = 0; i < nums.length - k + 1; i++) {
        //     res[i] = result[i];
        // }
        System.out.println(Arrays.toString(result));
        return res;
    }
}
